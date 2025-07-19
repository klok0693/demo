package org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.DraggableTool;

import static org.example.astero_demo.core.port.ui.UIConsrants.CONTACT_DIAMETER;

/**
 * Represent a special contact points, which allows {@link ShapeSelectionTool} to modify shape dimensions.<p>
 * Every point has it own {@link ContactAlignment}, based on which all necessary calculations are made
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ContactPoint<E> extends DraggableTool<E> {

    protected static final double RADIUS = CONTACT_DIAMETER / 2;

    private final SelectionFrame<E> selectionTool;

    private final ContactAlignment alignment;

    protected ContactPoint(
            final SelectionFrame<E> selectionTool,
            final CanvasAdapter adapter,
            final int layer,
            final ContactAlignment alignment) {
        super(CONTACT_DIAMETER, CONTACT_DIAMETER, layer, adapter, null);
        this.selectionTool = selectionTool;
        this.alignment = alignment;
        setEnabled(true);
    }

    public void update(final double x, final double y, final double width, final double height) {
        this.alignment.updateContact(this, x, y, width, height);
        setVisible(true);
    }

    public boolean isInBounds(final double x, final double y) {
        final double dx = x - this.x; // Distance from the point to the circle's center (x-axis)
        final double dy = y - this.y; // Distance from the point to the circle's center (y-axis)
        final double distanceSquared = dx * dx + dy * dy; // Square of the distance to the center
        final double radiusSquared = RADIUS * RADIUS; // Square of the radius

        return distanceSquared <= radiusSquared; // Check if the point is within the circle
    }

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        if (isVisible() && isEnabled() && isInBounds(mouseX, mouseY)) {
            setActive(true);
            selectionTool.setVisible(true);
            return true;
        }
        return false;
    }

    @Override
    public void update(final double mouseX, final double mouseY) {
        alignment.updateParentTool(selectionTool, mouseX, mouseY);
    }

    @Override
    public void performOperation(final double[] toolValues) {
        final double parentX = selectionTool.getX();
        final double parentY = selectionTool.getY();
        final double parentWidth = selectionTool.getWidth();
        final double parentHeight = selectionTool.getHeight();
        adapter.modifySelectedShape(parentX, parentY, parentWidth, parentHeight);
    }

    public void setValues(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
