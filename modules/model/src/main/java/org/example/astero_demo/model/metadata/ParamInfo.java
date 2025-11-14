package org.example.astero_demo.model.metadata;

import lombok.Getter;
import lombok.Setter;

import static java.lang.String.valueOf;

/**
 * Helper class, combining the {@link ShapeParam} with values.<p>
 * Used to transfer and store the information about params modification
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public final class ParamInfo {
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

    public static ParamInfo create(final ShapeParam param, final double newValue) {
        return create(param, valueOf(newValue));
    }

    public static ParamInfo create(final ShapeParam param, final int newValue) {
        return create(param, valueOf(newValue));
    }

    @Override
    public String toString() {
        return String.format("%s: new %s, old %s", param.name(), newValue, oldValue);
    }
}
