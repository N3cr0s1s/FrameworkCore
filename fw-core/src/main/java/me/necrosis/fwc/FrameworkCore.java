package me.necrosis.fwc;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.necrosis.fwc.utils.LoggerUtil;
import org.jetbrains.annotations.NotNull;

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

        AbstractModule abstractModule = LoggerUtil.processTimeLogger("Creating common module", log, this::configureServices);
        this.injector = LoggerUtil.processTimeLogger("Creating injector", log, () -> Guice.createInjector(abstractModule));

        log.trace("{} initialization done.", Constants.FRAMEWORK_CORE_NAME);

        handleStart(options);
    }

    public FrameworkCore() {
        this(FwOptions.getDefault());
    }

    private void handleStart(@NotNull FwOptions options) {
        if (!options.isAutoStart()) {
            return;
        }
        LoggerUtil.processTimeLogger("Calling start method", log, this::onStart);
        LoggerUtil.processTimeLogger("Calling end method", log, this::onEnd);
    }

    /**
     * Register services to IOC container.
     * <br><br>
     * Usage: {@code bind(Interface.class).to(Implementation.class);}
     */
    public abstract AbstractModule configureServices();

    public abstract void onStart();

    public void onEnd() {}
}
