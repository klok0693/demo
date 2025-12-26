package org.example.demo.realization.level.transport.clipboard.event;

import org.example.demo.realization.level.transport.ApplicationEvent;

public abstract sealed class ClipboardEvent extends ApplicationEvent
        permits CopyEvent, CutEvent, PasteEvent {
}
