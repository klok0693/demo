package org.example.astero_demo.realization.level.transport.logic.event;

import org.example.astero_demo.realization.level.transport.ApplicationEvent;

/**
 * LogicEvent is an abstract class representing an event in the logic layer of the application.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract sealed class LogicEvent extends ApplicationEvent
        permits ParamEvent, RemoveShapeEvent, UndoLastOperationEvent {
}
