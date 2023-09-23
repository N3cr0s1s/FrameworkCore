package me.necrosis.fwc.plugins.event;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class EventBusModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventBusPlugin.class).to(EventBusPlugin.class).in(Scopes.SINGLETON);
    }
}
