package org.example.astero_demo.core.port.ui.canvas.shape;

import javafx.scene.paint.Color;
import lombok.Getter;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;

/**
 * Represents an shape element that can be drawn on a canvas.<p>
 * <b>Note:</b> this class has a natural ordering that is inconsistent with equals.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface ShapeElement<E extends Object> extends CanvasElement<E>, Comparable<ShapeElement<E>> {

    int getModelRelatedId();

    int getLayer();

    Color getFillColor();

    @Override
    default int compareTo(final ShapeElement o) {
        return Integer.compare(getLayer(), o.getLayer());
    }
}
