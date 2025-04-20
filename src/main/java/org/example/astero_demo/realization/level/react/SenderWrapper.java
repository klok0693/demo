package org.example.astero_demo.realization.level.react;

import org.example.astero_demo.logic.event.ui.LogicEvent;

public abstract class SenderWrapper {
    private final Pipe pipe;

    protected SenderWrapper(final Pipe pipe) {
        this.pipe = pipe;
    }

    protected void send(final LogicEvent event) {
        pipe.addToPipe(event);
    }
}
