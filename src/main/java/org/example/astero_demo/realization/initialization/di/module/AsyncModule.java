package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.controller.ui.ControllerAdapter;
import org.example.astero_demo.realization.async.logic.BackgroundExecutor;
import org.example.astero_demo.realization.async.logic.LogicEventProcessorAsyncWrapper;
import org.example.astero_demo.realization.async.ui.FXExecutor;
import org.example.astero_demo.realization.async.ui.RootAdapterAsyncWrapper;

import java.util.concurrent.Executor;

/**
 * DI config for async wrappers
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class AsyncModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BackgroundExecutor.class).in(Scopes.SINGLETON);
        bind(LogicEventProcessor.class).to(LogicEventProcessorAsyncWrapper.class).in(Scopes.SINGLETON);

        bind(FXExecutor.class).in(Scopes.SINGLETON);
        bind(ControllerAdapter.class).to(RootAdapterAsyncWrapper.class).in(Scopes.SINGLETON);
    }
}
