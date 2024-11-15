package org.example.astero_demo.controller;

import org.example.astero_demo.logic.event.ui.LogicEvent;

public interface LogicEventProcessor {

    void process(LogicEvent e);
}
