package org.example.astero_demo.realization.async.wrappers;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.ShapeCanvasAdapter;
import org.example.astero_demo.realization.async.AppExecutor;

public class CanvasAdapterWrapper implements CanvasAdapter {
    private final CanvasAdapter wrappedAdapter;
    private final AppExecutor executor;

    @Inject
    public CanvasAdapterWrapper(final ShapeCanvasAdapter wrappedAdapter, final AppExecutor executor) {
        this.wrappedAdapter = wrappedAdapter;
        this.executor = executor;
    }

    @Override
    public void primaryMouseBtnPressed(final double x, final double y) {
        wrappedAdapter.primaryMouseBtnPressed(x, y);
    }

    @Override
    public void createNewShapeAt(
            final double x,
            final double y,
            final double width,
            final double height) {
        executor.execute(() -> wrappedAdapter.createNewShapeAt(x, y, width, height));
    }

    @Override
    public void modifySelectedShape(
            final double x,
            final double y,
            final double width,
            final double height) {
        executor.execute(() -> wrappedAdapter.modifySelectedShape(x, y, width, height));
    }

    @Override
    public void moveSelectedShapeTo(final double x, final double y) {
        executor.execute(() -> wrappedAdapter.moveSelectedShapeTo(x, y));
    }

    @Override
    public Shape selectElement(final double x, final double y) {
        return wrappedAdapter.selectElement(x, y);
    }

    @Override
    public Shape selectElement(final int id) {
        return wrappedAdapter.selectElement(id);
    }

    @Override
    public void setParent(final ParentAdapter parentAdapter) {
        wrappedAdapter.setParent(parentAdapter);
    }

    @Override
    public void update() {
        wrappedAdapter.update();
    }
}
