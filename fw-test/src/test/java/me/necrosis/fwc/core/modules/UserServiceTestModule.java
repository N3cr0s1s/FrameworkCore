package me.necrosis.fwc.core.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import me.necrosis.fwc.core.services.DataAccess;
import me.necrosis.fwc.core.services.UserService;
import me.necrosis.fwc.core.services.impl.MemoryDataAccess;
import me.necrosis.fwc.core.services.impl.UserServiceImpl;

public class UserServiceTestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DataAccess.class).to(MemoryDataAccess.class).in(Scopes.SINGLETON);
        bind(UserService.class).to(UserServiceImpl.class).in(Scopes.SINGLETON);
    }
}
