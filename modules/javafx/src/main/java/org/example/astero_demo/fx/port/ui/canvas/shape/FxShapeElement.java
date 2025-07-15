package org.example.astero_demo.fx.port.ui.canvas.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.example.astero_demo.core.port.ui.canvas.shape.ShapeElement;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;

/**
 * JavaFX's realization of {@link ShapeElement}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
@Getter
public abstract class FxShapeElement extends FxCanvasElement implements ShapeElement<GraphicsContext> {
    private final int modelRelatedId;
    private final int layer;
    protected Color fillColor;

    protected FxShapeElement(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color fillColor) {
        super(x, y, width, height);
        this.modelRelatedId = modelRelatedId;
        this.layer = layer;
        this.fillColor = fillColor;
    }
}
