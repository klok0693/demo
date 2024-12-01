package org.example.astero_demo.realization.async.wrappers.adapter;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.canvas.ShapeCanvasAdapter;
import org.example.astero_demo.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.realization.async.AppExecutor;

import static org.example.astero_demo.realization.logging.MarkerStorage.USER_INPUT_MARKER;

/**
 * Wrapper class that adapts interactions with a canvas
 * by delegating to a wrapped {@link ShapeCanvasAdapter} instance.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class CanvasAdapterWrapper extends UpdatableAdapterAsyncWrapper<CanvasAdapter> implements CanvasAdapter {

    @Inject
    public CanvasAdapterWrapper(final ShapeCanvasAdapter wrappedAdapter, final AppExecutor executor) {
        super(executor, wrappedAdapter);
    }

    @Override
    public void primaryMouseBtnPressed(final double x, final double y, final boolean isAdditional) {
        log.debug(USER_INPUT_MARKER, "Press canvas at {},{}", x, y);
        wrappedElement.primaryMouseBtnPressed(x, y, isAdditional);
    }

    @Override
    public void selectNextShapeAt(double x, double y) {
        log.debug(USER_INPUT_MARKER, "Select next shape at {},{}", x, y);
        wrappedElement.selectNextShapeAt(x, y);
    }

    @Override
    public void selectMultiple(double x, double y) {
        log.debug(USER_INPUT_MARKER, "Add Selection at {},{}", x, y);
        wrappedElement.selectMultiple(x, y);
    }

    @Override
    public void createNewShapeAt(
            final double x,
            final double y,
            final double width,
            final double height) {
        log.debug(USER_INPUT_MARKER, "Create new shape at {},{}, size: {},{}", x, y, width, height);
        executeInBackground(() -> wrappedElement.createNewShapeAt(x, y, width, height));
    }

    @Override
    public void modifySelectedShape(
            final double x,
            final double y,
            final double width,
            final double height) {
        log.debug(USER_INPUT_MARKER, "Modify selected shape to x:{}, y:{}, w:{}, h{}", x, y, width, height);
        executeInBackground(() -> wrappedElement.modifySelectedShape(x, y, width, height));
    }

    @Override
    public void moveSelectedShapeTo(final double x, final double y) {
        log.debug(USER_INPUT_MARKER, "Move selected shape to {},{}", x, y);
        executeInBackground(() -> wrappedElement.moveSelectedShapeTo(x, y));
    }

    @Override
    public double[] getLocalCursorPosition() {
        return wrappedElement.getLocalCursorPosition();
    }

    @Override
    public void switchMode(final UIMode mode) {
        executeInFXThread(() -> wrappedElement.switchMode(mode));
    }
}
