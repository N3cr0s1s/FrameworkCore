package me.necrosis.fwc.core.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import me.necrosis.fwc.core.services.TransientService;
import me.necrosis.fwc.core.services.SingletonService;
import me.necrosis.fwc.core.services.impl.TransientServiceImpl;

public class ScopeTestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TransientService.class).to(TransientServiceImpl.class).in(Scopes.NO_SCOPE);
        bind(SingletonService.class).to(TransientServiceImpl.class).in(Scopes.SINGLETON);
    }
}
