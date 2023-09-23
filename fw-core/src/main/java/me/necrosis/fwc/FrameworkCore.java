package me.necrosis.fwc;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
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

    private final Injector injector;

    /**
     * FrameworkCore entry point
     */
    public FrameworkCore(@NotNull FwOptions options) {
        Preconditions.checkNotNull(options);

        log.trace("{} initialization started...", Constants.FRAMEWORK_CORE_NAME);

        Collection<Module> modules = registerPlugins();
        AbstractModule commonModule = LoggerUtil.processTimeLogger("Creating modules", log, this::configureServices);
        modules.add(commonModule);
        this.injector = LoggerUtil.processTimeLogger(
                "Creating injector", log,
                () -> Guice.createInjector(modules));

        modules.stream()
                .filter(x -> !x.equals(commonModule))
                .forEach(x -> log.trace("Plugin registered: {}", x.getClass()));

        log.trace("{} initialization done.", Constants.FRAMEWORK_CORE_NAME);

        registerExceptionHandler(options);
        handleStart(options);
    }

    public FrameworkCore() {
        this(FwOptions.getDefault());
    }

    private void registerExceptionHandler(@NotNull FwOptions options) {
        if (!options.isGlobalExceptionHandler()) {
            return;
        }

        LoggerUtil.processTimeLogger(
                "Registering global exception handler", log,
                () -> Thread.setDefaultUncaughtExceptionHandler(this::onGlobalException)
        );
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

    public void onGlobalException(Thread t, Throwable e) {
        log.error("Global exception handler received an exception.", e);
    }

    public void onFwException(Thread t, FwException e) {
        log.error("{}", e.getMessage());
        log.error("---- {}", e.getException().getMessage());
    }
}
