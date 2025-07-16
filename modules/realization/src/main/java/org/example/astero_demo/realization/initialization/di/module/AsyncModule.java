package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.ui.RootAdapter;
import org.example.astero_demo.core.controller.ui.ControllerAdapter;
import org.example.astero_demo.core.logic.LogicShapeProcessor;
import org.example.astero_demo.core.logic.ShapeProcessor;
import org.example.astero_demo.realization.level.async.logic.BackgroundExecutor;
import org.example.astero_demo.realization.level.async.logic.EventProcessorAsyncWrapper;
import org.example.astero_demo.realization.level.async.logic.RunnableWrapper;
import org.example.astero_demo.realization.level.async.ui.ForegroundExecutor;
import org.example.astero_demo.realization.level.async.ui.RootAdapterAsyncWrapper;
import org.example.astero_demo.realization.level.transport.ChannelMock;
import org.example.astero_demo.realization.level.transport.logic_event.LogicEventReceiverWrapper;
import org.example.astero_demo.realization.level.transport.logic_event.LogicEventSenderWrapper;

import java.util.List;

/**
 * DI config for async wrappers
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class AsyncModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ShapeProcessor.class).to(LogicEventSenderWrapper.class).in(Scopes.SINGLETON);
        bind(ControllerAdapter.class).to(RootAdapterAsyncWrapper.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public RootAdapterAsyncWrapper provideRootAdapterAsynchWrapper(
            final RootAdapter wrappedElement, final ForegroundExecutor executor) {
        return new RootAdapterAsyncWrapper(wrappedElement, executor);
    }

    @Inject
    @Provides
    @Singleton
    public EventProcessorAsyncWrapper provideEventProcessorAsyncWrapper(
            final LogicShapeProcessor processor,
            final BackgroundExecutor executor) {
        return new EventProcessorAsyncWrapper(processor, executor);
    }

    @Inject
    @Provides
    @Singleton
    public LogicEventSenderWrapper provideLogicEventSenderWrapper(final ChannelMock channelMock) {
        return new LogicEventSenderWrapper(channelMock);
    }

    @Inject
    @Provides
    @Singleton
    public LogicEventReceiverWrapper provideLogicEventReceiverWrapper(final EventProcessorAsyncWrapper eventProcessor) {
        return new LogicEventReceiverWrapper(eventProcessor);
    }

    @Inject
    @Provides
    @Singleton
    public ChannelMock providePipe(final LogicEventReceiverWrapper receiver) {
        return new ChannelMock(List.of(receiver));
    }

    @Inject
    @Provides
    @Singleton
    public BackgroundExecutor provideBackgroundExecutor(final RunnableWrapper wrapper) {
        return new BackgroundExecutor(wrapper);
    }
}
