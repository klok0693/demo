package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;

/**
 * Abstract class representing an event with shape parameters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public abstract class ParamEvent extends LogicEvent {
    protected final ParamInfo[] paramInfos;

    protected ParamEvent(final ParamInfo[] paramInfos) {
        this.paramInfos = ArrayUtils.removeAllOccurences(paramInfos, null);
    }
}
