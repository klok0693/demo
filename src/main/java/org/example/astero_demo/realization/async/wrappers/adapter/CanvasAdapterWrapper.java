package org.example.astero_demo.realization.async.wrappers.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.canvas.ShapeCanvasAdapter;
import org.example.astero_demo.realization.async.AppExecutor;

public class CanvasAdapterWrapper extends UpdatableAdapterAsyncWrapper<CanvasAdapter> implements CanvasAdapter {

    @Inject
    public CanvasAdapterWrapper(final ShapeCanvasAdapter wrappedAdapter, final AppExecutor executor) {
        super(executor, wrappedAdapter);
    }

    @Override
    public void primaryMouseBtnPressed(final double x, final double y) {
        wrappedElement.primaryMouseBtnPressed(x, y);
    }

    @Override
    public void createNewShapeAt(
            final double x,
            final double y,
            final double width,
            final double height) {
        executeInBackground(() -> wrappedElement.createNewShapeAt(x, y, width, height));
    }

    @Override
    public void modifySelectedShape(
            final double x,
            final double y,
            final double width,
            final double height) {
        executeInBackground(() -> wrappedElement.modifySelectedShape(x, y, width, height));
    }

    @Override
    public void moveSelectedShapeTo(final double x, final double y) {
        executeInBackground(() -> wrappedElement.moveSelectedShapeTo(x, y));
    }

    @Override
    public Shape selectElement(final double x, final double y) {
        return wrappedElement.selectElement(x, y);
    }

    @Override
    public Shape selectElement(final int id) {
        return wrappedElement.selectElement(id);
    }

    @Override
    public boolean hasInjectedField(final String fieldName) {
        return super.hasInjectedField(fieldName) || "canvasRoot".equals(fieldName);
    }
}
