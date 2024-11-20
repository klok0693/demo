package org.example.astero_demo.port.ui.canvas.tool.draggable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.port.ui.canvas.tool.ShapeSelectionTool;

public class ContactPoint extends DraggableTool {
    private static final double DIAMETER = 16.0;
    private static final double RADIUS = DIAMETER / 2;

    private final ShapeSelectionTool selectionTool;
    private final CanvasAdapter adapter;

    private final ContactAlignment alignment;

    public ContactPoint(
            final ShapeSelectionTool selectionTool,
            final CanvasAdapter adapter,
            final int layer,
            final Color fillColor,
            final ContactAlignment alignment) {
        super(DIAMETER, DIAMETER, layer);
        this.selectionTool = selectionTool;
        this.adapter = adapter;
        this.fillColor = fillColor;
        this.alignment = alignment;
        this.isVisible = false;
    }

    public void update(final double x, final double y, final double width, final double height) {
        this.alignment.updateContact(this, x, y, width, height);
        this.isVisible = true;
    }

    public void setValues(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x - RADIUS, y - RADIUS, DIAMETER, DIAMETER);
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
        if (isVisible && isInBounds(mouseX, mouseY)) {
            this.isActive = true;
            selectionTool.makeVisible();
            return true;
        }
        return false;
    }

    @Override
    protected void update(final double mouseX, final double mouseY) {
        alignment.updateParentTool(selectionTool, mouseX, mouseY);
    }

    @Override
    protected void performOperation(final double[] toolValues) {
        final double parentX = selectionTool.getX();
        final double parentY = selectionTool.getY();
        final double parentWidth = selectionTool.getWidth();
        final double parentHeight = selectionTool.getHeight();
        adapter.modifySelectedShape(parentX, parentY, parentWidth, parentHeight);
    }
}
