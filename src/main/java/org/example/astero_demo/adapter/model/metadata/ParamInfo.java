package org.example.astero_demo.adapter.model.metadata;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ParamInfo {
    private final ShapeParam param;
    private final String newValue;
    @Setter
    private String oldValue;

    private ParamInfo(final ShapeParam param, final String newValue) {
        this.param = param;
        this.newValue = newValue;
    }

    public static ParamInfo create(final ShapeParam param, final String newValue) {
        return new ParamInfo(param, newValue);
    }

    @Override
    public String toString() {
        return String.format("%s: new %s, old %s", param.name(), newValue, oldValue);
    }
}
