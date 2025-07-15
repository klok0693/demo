package org.example.astero_demo.fx.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.fx.initialization.multithreading.FxExecutor;
import org.example.astero_demo.fx.initialization.multithreading.FxRunnableWrapper;
import org.example.astero_demo.realization.level.async.logic.RunnableWrapper;
import org.example.astero_demo.realization.level.async.ui.ForegroundExecutor;

public class FxAsynchModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RunnableWrapper.class).to(FxRunnableWrapper.class).in(Scopes.SINGLETON);
        bind(ForegroundExecutor.class).to(FxExecutor.class).in(Scopes.SINGLETON);
    }
}
