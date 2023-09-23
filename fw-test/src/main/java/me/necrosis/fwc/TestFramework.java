package me.necrosis.fwc;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import me.necrosis.fwc.services.DataAccess;
import me.necrosis.fwc.services.SingletonService;
import me.necrosis.fwc.services.TransientService;
import me.necrosis.fwc.services.UserService;
import me.necrosis.fwc.services.impl.MemoryDataAccess;
import me.necrosis.fwc.services.impl.TransientServiceImpl;
import me.necrosis.fwc.services.impl.UserServiceImpl;

public class TestFramework extends FrameworkCore {

    @Override
    public AbstractModule configureServices() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataAccess.class).to(MemoryDataAccess.class).in(Scopes.SINGLETON);
                bind(UserService.class).to(UserServiceImpl.class).in(Scopes.SINGLETON);

                bind(TransientService.class).to(TransientServiceImpl.class).in(Scopes.NO_SCOPE);
                bind(SingletonService.class).to(TransientServiceImpl.class).in(Scopes.SINGLETON);
            }
        };
    }
}
