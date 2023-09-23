package me.necrosis.fwc.test.fw;

import me.necrosis.fwc.core.frameworks.EventBusFramework;
import me.necrosis.fwc.plugins.event.EventBusPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PluginTest {

    @Test
    public void checkIsPluginRegistered() {
        EventBusFramework eventBusFramework = new EventBusFramework();
        EventBusPlugin instance = eventBusFramework.getInjector().getInstance(EventBusPlugin.class);

        Assertions.assertNotNull(instance);
    }

}
