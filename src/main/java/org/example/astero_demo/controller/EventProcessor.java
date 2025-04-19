package org.example.astero_demo.controller;

import org.example.astero_demo.logic.event.ui.LogicEvent;

/**
 * Functional interface for processing LogicEvents
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface EventProcessor {

    void process(LogicEvent e);


}
