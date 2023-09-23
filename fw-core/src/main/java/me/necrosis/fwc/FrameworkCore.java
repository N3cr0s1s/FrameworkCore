package me.necrosis.fwc;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.necrosis.fwc.utils.LoggerUtil;

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
    public FrameworkCore() {
        log.trace("{} initialization started...",Constants.FRAMEWORK_CORE_NAME);

        AbstractModule abstractModule = LoggerUtil.processLogger("Creating common module", log, this::configureServices);
        this.injector = LoggerUtil.processLogger("Creating injector",log, () -> Guice.createInjector(abstractModule));

        log.trace("{} initialization done.",Constants.FRAMEWORK_CORE_NAME);
    }

    /**
     * Register services to IOC container.
     * <br><br>
     * Usage: {@code bind(Interface.class).to(Implementation.class);}
     */
    public abstract AbstractModule configureServices();

}
