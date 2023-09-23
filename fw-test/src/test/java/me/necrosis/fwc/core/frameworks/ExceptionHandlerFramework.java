package me.necrosis.fwc.core.frameworks;

import com.google.inject.AbstractModule;
import me.necrosis.fwc.FrameworkCore;
import me.necrosis.fwc.FwOptions;
import me.necrosis.fwc.core.modules.ScopeTestModule;
import me.necrosis.fwc.exception.FwException;

import java.util.function.BiConsumer;

public class ExceptionHandlerFramework extends FrameworkCore {

    private final Runnable runnable;
    private final BiConsumer<Thread, FwException> threadThrowableBiConsumer;

    public ExceptionHandlerFramework(Runnable runnable, BiConsumer<Thread,FwException> threadThrowableBiConsumer) {
        super(FwOptions.builder().autoInit(false).build());
        this.runnable = runnable;
        this.threadThrowableBiConsumer = threadThrowableBiConsumer;

        this.init();
    }

    @Override
    public AbstractModule configureServices() {
        return new ScopeTestModule();
    }

    @Override
    public void onStart() throws Throwable {
        this.runnable.run();
    }

    @Override
    public void onFwException(Thread t, FwException e) {
        super.onFwException(t,e);
        this.threadThrowableBiConsumer.accept(t,e);
    }
}
