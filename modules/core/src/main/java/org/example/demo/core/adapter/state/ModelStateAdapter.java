package org.example.demo.core.adapter.state;

import org.example.demo.model.entity.Shape;
import org.example.demo.model.entity.ShapeFactory;
import org.example.demo.model.entity.ShapeType;
import org.example.demo.model.metadata.ShapeParam;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.context.state.MutableModelState;

import javax.annotation.Nullable;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

/**
 * Adapter for the {@link ModelState}. All interactions with the state
 * should be performed through this adapter
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ModelStateAdapter {
    private final ShapeFactory factory;
    private final MutableModelState modelState;

    public ModelStateAdapter(final ShapeFactory factory, final MutableModelState modelState) {
        this.factory = factory;
        this.modelState = modelState;
    }

    /**
     * Gets the specified parameter of a shape with the given ID.
     *
     * @return The value of the specified parameter, or null if the shape <p>
     *          with the given id does not exist or param is 'ID'
     */
    @Nullable
    public String getShapeParam(final int id, final ShapeParam param) {
        final Shape shape = modelState.getShape(id);
        if (shape == null) {
            return null;
        }
        return switch (param) {
            case X -> shape.getX();
            case Y -> shape.getY();
            case WIDTH -> shape.getWidth();
            case HEIGHT -> shape.getHeight();
            case PRIORITY -> shape.getPriority();
            case COLOR -> shape.getColor();
            case TYPE -> valueOf(shape.getType());
            default -> null;
        };
    }

    /**
     * Modify the parameter of a shape<p>
     * <b>Note:</b> ID parameter not modified
     */
    public void modifyShapeParam(final int id, final ShapeParam param, final String newValue) {
        final Shape shape = modelState.getShape(id);
        if (shape == null) {
            return;
        }
        switch (param) {
            case ID: shape.setId(parseInt(newValue)); break;
            case X: shape.setX(newValue); break;
            case Y: shape.setY(newValue); break;
            case WIDTH: shape.setWidth(newValue); break;
            case HEIGHT: shape.setHeight(newValue); break;
            case PRIORITY: shape.setPriority(newValue); break;
            case COLOR: shape.setColor(newValue);
        }
    }

    /**
     * Save new shape with given parameters
     *
     * @return id if the saved shape
     */
    public int saveShape(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {
        return this.saveShape(null, priority, x, y, width, height, color, type);
    }

    /**
     * Save new shape with given parameters and id. Necessary for some editor's operations
     *
     * @return id if the saved shape
     */
    public int saveShape(
            final Integer id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {
        final Shape shape = factory.createShape(id, priority, x, y, width, height, color, type);
        modelState.saveShape(shape);
        return shape.getId();
    }

    /**
     * Remove shape with given id
     *
     * @return removed shape. Necessary to save the removed data for undo commands
     */
    public Shape removeShape(final int id) {
        final Shape removed = modelState.getShape(id);
        modelState.removeShape(id);
        return removed;
    }
}
