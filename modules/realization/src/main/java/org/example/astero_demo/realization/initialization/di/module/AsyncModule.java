package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.ui.RootAdapter;
import org.example.astero_demo.core.controller.ui.ControllerAdapter;
import org.example.astero_demo.core.logic.ClipboardProcessor;
import org.example.astero_demo.core.logic.LogicClipboardProcessor;
import org.example.astero_demo.core.logic.LogicShapeProcessor;
import org.example.astero_demo.core.logic.ShapeProcessor;
import org.example.astero_demo.realization.level.async.BackgroundExecutor;
import org.example.astero_demo.realization.level.async.clipboard.ClipboardProcessorAsyncWrapper;
import org.example.astero_demo.realization.level.async.logic.EventProcessorAsyncWrapper;
import org.example.astero_demo.realization.level.async.RunnableWrapper;
import org.example.astero_demo.realization.level.async.NonBlockingForegroundExecutor;
import org.example.astero_demo.realization.level.async.ui.RootAdapterAsyncWrapper;
import org.example.astero_demo.realization.level.transport.Channel;
import org.example.astero_demo.realization.level.transport.ChannelMock;
import org.example.astero_demo.realization.level.transport.clipboard.ClipboardEventReceiverWrapper;
import org.example.astero_demo.realization.level.transport.clipboard.ClipboardEventSenderWrapper;
import org.example.astero_demo.realization.level.transport.logic.LogicEventReceiverWrapper;
import org.example.astero_demo.realization.level.transport.logic.LogicEventSenderWrapper;

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
        bind(ClipboardProcessor.class).to(ClipboardEventSenderWrapper.class).in(Scopes.SINGLETON);
        bind(ControllerAdapter.class).to(RootAdapterAsyncWrapper.class).in(Scopes.SINGLETON);

        bind(Channel.class).to(ChannelMock.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public RootAdapterAsyncWrapper provideRootAdapterAsyncWrapper(
            final RootAdapter wrappedElement, final NonBlockingForegroundExecutor executor) {
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
    public ClipboardProcessorAsyncWrapper provideClipboardProcessorAsyncWrapper(
            final LogicClipboardProcessor processor,
            final BackgroundExecutor executor) {
        return new ClipboardProcessorAsyncWrapper(processor, executor);
    }

    @Inject
    @Provides
    @Singleton
    public LogicEventSenderWrapper provideLogicEventSenderWrapper(final Channel channelMock) {
        return new LogicEventSenderWrapper(channelMock);
    }

    @Inject
    @Provides
    @Singleton
    public ClipboardEventSenderWrapper provideClipboardEventSenderWrapper(final Channel channelMock) {
        return new ClipboardEventSenderWrapper(channelMock);
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
    public ClipboardEventReceiverWrapper provideClipboardEventReceiverWrapper(final ClipboardProcessorAsyncWrapper clipboardProcessor) {
        return new ClipboardEventReceiverWrapper(clipboardProcessor);
    }

    @Inject
    @Provides
    @Singleton
    public ChannelMock providePipe(final LogicEventReceiverWrapper logicReceiver, ClipboardEventReceiverWrapper clipboardReceiver) {
        return new ChannelMock(List.of(logicReceiver, clipboardReceiver));
    }

    @Inject
    @Provides
    @Singleton
    public BackgroundExecutor provideBackgroundExecutor(final RunnableWrapper wrapper) {
        return new BackgroundExecutor(wrapper);
    }
}
