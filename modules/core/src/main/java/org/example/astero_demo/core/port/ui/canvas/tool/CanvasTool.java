package org.example.astero_demo.core.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;

/**
 * Base class for all canvas tools. Tool is a specific element, provided<p>
 * ability to perform various operations with canvas shape elements
 * <p>
 * <b>Note:</b> this class has a natural ordering that is inconsistent with equals.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CanvasTool<E extends Object> extends CanvasElement<E>, Comparable<CanvasTool<E>> {

    int getLayer();

    boolean isVisible();

    void setVisible(boolean isVisible);

    boolean isActive();

    void setActive(boolean isActive);

    boolean isEnabled();

    void setEnabled(boolean isEnabled);

    /**
     * Every class, adding new mutable fields, must override this method
     */
    double[] reset();

    @Override
    default int compareTo(final CanvasTool o) {
        return Integer.compare(getLayer(), o.getLayer());
    }
}
