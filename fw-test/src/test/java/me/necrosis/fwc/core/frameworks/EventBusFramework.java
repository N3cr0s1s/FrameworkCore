package me.necrosis.fwc.core.frameworks;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import me.necrosis.fwc.FrameworkCore;
import me.necrosis.fwc.plugins.event.EventBusModule;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class EventBusFramework extends FrameworkCore {
    @Override
    public AbstractModule configureServices() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                super.configure();
            }
        };
    }

    @Override
    public void onStart() throws Throwable {
    }

    @NotNull
    @Override
    public Collection<Module> registerPlugins() {
        Collection<Module> modules = super.registerPlugins();
        modules.add(new EventBusModule());
        return modules;
    }
}
