package me.necrosis.fwc;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import lombok.extern.slf4j.Slf4j;
import me.necrosis.fwc.entities.User;
import me.necrosis.fwc.services.DataAccess;
import me.necrosis.fwc.services.SingletonService;
import me.necrosis.fwc.services.TransientService;
import me.necrosis.fwc.services.UserService;
import me.necrosis.fwc.services.impl.MemoryDataAccess;
import me.necrosis.fwc.services.impl.TransientServiceImpl;
import me.necrosis.fwc.services.impl.UserServiceImpl;

import java.util.concurrent.TimeUnit;

@Slf4j
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

    @Override
    public void onStart() {
        UserService instance = getInjector().getInstance(UserService.class);
        User user = instance.createUser("Test", "Last", 2);
        log.info("User created {}",user);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEnd() {
        log.info("Framework ended");
    }
}
