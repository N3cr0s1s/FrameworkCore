package me.necrosis.fwc.core.frameworks;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Scopes;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.necrosis.fwc.FrameworkCore;
import me.necrosis.fwc.FwOptions;
import me.necrosis.fwc.core.entities.User;
import me.necrosis.fwc.core.services.DataAccess;
import me.necrosis.fwc.core.services.SingletonService;
import me.necrosis.fwc.core.services.TransientService;
import me.necrosis.fwc.core.services.UserService;
import me.necrosis.fwc.core.services.impl.MemoryDataAccess;
import me.necrosis.fwc.core.services.impl.TransientServiceImpl;
import me.necrosis.fwc.core.services.impl.UserServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@Getter
public class TestFramework extends FrameworkCore {

    private final AbstractModule abstractModule;
    private boolean started = false;
    private boolean ended = false;

    public TestFramework(AbstractModule abstractModule){
        super(FwOptions.builder().autoInit(false).build());
        Preconditions.checkNotNull(abstractModule);
        this.abstractModule = abstractModule;
        this.init();
    }

    @Override
    public AbstractModule configureServices() {
        return this.abstractModule;
    }

    @Override
    public void onStart() {
        this.started = true;
    }

    @Override
    public void onEnd(boolean successfullyEnded) {
        this.ended = true;
    }

    @NotNull
    @Override
    public Collection<Module> registerPlugins() {
        Collection<Module> modules = super.registerPlugins();
        return modules;
    }
}
