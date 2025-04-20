package org.example.astero_demo.logic;

import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.model.metadata.ParamInfo;

/**
 * Interface for processing LogicEvents
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface EventProcessor {
    void process(LogicEvent e);

    void createShape(ParamInfo[] paramInfos);
}
