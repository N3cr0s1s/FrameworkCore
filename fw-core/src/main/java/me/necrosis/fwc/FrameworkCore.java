package me.necrosis.fwc;

import com.google.inject.AbstractModule;

/**
 * Framework core main class.
 */
public abstract class FrameworkCore extends AbstractModule {

    /**
     * Register services to IOC container.
     * <br><br>
     * Usage: {@code bind(Interface.class).to(Implementation.class);}
     */
    public void configureServices(){}

}
