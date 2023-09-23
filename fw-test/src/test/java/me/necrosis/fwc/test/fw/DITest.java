package me.necrosis.fwc.test.fw;


import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import me.necrosis.fwc.core.frameworks.TestFramework;
import me.necrosis.fwc.core.modules.ScopeTestModule;
import me.necrosis.fwc.core.services.SingletonService;
import me.necrosis.fwc.core.services.TransientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class DITest {
    private static Injector injector;

    @BeforeAll
    public static void setup() {
        injector = new TestFramework(new ScopeTestModule()).getInjector();
    }

    @Test
    @DisplayName("Check classes registered successfully")
    public void checkRegistrations() {
        TransientService transientService = injector.getInstance(TransientService.class);
        SingletonService singletonService = injector.getInstance(SingletonService.class);

        Assertions.assertNotNull(transientService);
        Assertions.assertNotNull(singletonService);
    }

    @Test
    @DisplayName("Check transient scope is working as it planned")
    public void checkScopesTransient() {
        TransientService transientServiceFirst = injector.getInstance(TransientService.class);
        UUID firstUUID = transientServiceFirst.getUUID();

        TransientService transientServiceSecond = injector.getInstance(TransientService.class);
        UUID secondUUID = transientServiceSecond.getUUID();

        Assertions.assertNotEquals(firstUUID,secondUUID);
    }

    @Test
    @DisplayName("Check transient scope is working as it planned")
    public void checkScopesSingleton() {
        SingletonService singletonFirst = injector.getInstance(SingletonService.class);
        UUID firstUUID = singletonFirst.getUUID();

        SingletonService singletonSecond = injector.getInstance(SingletonService.class);
        UUID secondUUID = singletonSecond.getUUID();

        Assertions.assertEquals(firstUUID,secondUUID);
    }
}
