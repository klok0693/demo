package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;

public abstract class ParamEvent extends LogicEvent {
    @Getter
    protected final ParamInfo[] paramInfos;

    protected ParamEvent(final ParamInfo[] paramInfos) {
        this.paramInfos = ArrayUtils.removeAllOccurences(paramInfos, null);
    }
}
