package me.necrosis.fwc;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.necrosis.fwc.exception.FwException;
import me.necrosis.fwc.utils.LoggerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Framework core main class.
 */
@Getter
@Slf4j
public abstract class FrameworkCore {

    @NotNull
    @Getter(AccessLevel.PROTECTED)
    private final FwOptions options;
    private Injector injector;

    /**
     * FrameworkCore entry point
     */
    public FrameworkCore(@NotNull FwOptions options) {
        this.options = Preconditions.checkNotNull(options);

        if (this.options.isAutoInit()) {
            this.init();
        }
    }

    public FrameworkCore() {
        this(FwOptions.getDefault());
    }

    public void init() {
        log.trace("{} initialization started...", Constants.FRAMEWORK_CORE_NAME);

        registerModules();
        handleStart(options);

        log.trace("{} initialization done.", Constants.FRAMEWORK_CORE_NAME);
    }

    private void registerModules() {
        final Injector injector;
        Collection<Module> modules = registerPlugins();
        AbstractModule commonModule = LoggerUtil.processTimeLogger("Creating modules", log, this::configureServices);

        Preconditions.checkNotNull(commonModule);

        modules.add(commonModule);
        injector = LoggerUtil.processTimeLogger(
                "Creating injector", log,
                () -> Guice.createInjector(modules));

        modules.stream()
                .filter(x -> !x.equals(commonModule))
                .forEach(x -> log.trace("Plugin registered: {}", x.getClass()));
        this.injector = injector;
    }

    private void handleStart(@NotNull FwOptions options) {
        if (!options.isAutoStart()) {
            return;
        }
        AtomicBoolean isException = new AtomicBoolean(false);
        LoggerUtil.processTimeLogger("Calling start method", log, () -> {
            try {
                this.onStart();
            } catch (Throwable e) {
                this.onFwException(Thread.currentThread(), new FwException("Start method exception", e));
                isException.set(true);
            }
        });
        LoggerUtil.processTimeLogger("Calling end method", log, () -> {
            try {
                this.onEnd(isException.get());
            } catch (Throwable e) {
                this.onFwException(Thread.currentThread(), new FwException("End method exception", e));
            }
        });
    }


    /**
     * Register services to IOC container.
     * <br><br>
     * Usage: {@code bind(Interface.class).to(Implementation.class);}
     */
    public abstract AbstractModule configureServices();

    public abstract void onStart() throws Throwable;

    public @NotNull Collection<Module> registerPlugins() {
        return new ArrayList<>();
    }

    public void onEnd(boolean successfullyEnded) throws Throwable {
    }

    public void onFwException(Thread t, FwException e) {
        log.error("{}", e.getMessage());
        log.error("---- {}", e.getException().getMessage());
    }
}
