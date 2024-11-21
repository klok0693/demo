package org.example.astero_demo.realization.async.wrappers.adapter;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesPanelAdapter;
import org.example.astero_demo.realization.async.AppExecutor;

import static org.example.astero_demo.realization.logging.MarkerStorage.USER_INPUT_MARKER;

@Slf4j
public class PropertiesAdapterWrapper extends UpdatableAdapterAsyncWrapper<PropertiesAdapter> implements PropertiesAdapter {

    @Inject
    protected PropertiesAdapterWrapper(final AppExecutor executor, final PropertiesPanelAdapter wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public void updateX(final String x) {
        log.debug(USER_INPUT_MARKER, "Update selected shape's x:{}", x);
        executeInBackground(() -> wrappedElement.updateX(x));
    }

    @Override
    public void updateY(final String y) {
        log.debug(USER_INPUT_MARKER, "Update selected shape's y:{}", y);
        executeInBackground(() -> wrappedElement.updateY(y));
    }

    @Override
    public void updateWidth(final String width) {
        log.debug(USER_INPUT_MARKER, "Update selected shape's width:{}", width);
        executeInBackground(() -> wrappedElement.updateWidth(width));
    }

    @Override
    public void updateHeight(final String height) {
        log.debug(USER_INPUT_MARKER, "Update selected shape's height:{}", height);
        executeInBackground(() -> wrappedElement.updateHeight(height));
    }

    @Override
    public void updateLayer(final String layer) {
        log.debug(USER_INPUT_MARKER, "Update selected shape's layer:{}", layer);
        executeInBackground(() -> wrappedElement.updateLayer(layer));
    }

    @Override
    public void updateColor(final String color) {
        log.debug(USER_INPUT_MARKER, "Update selected shape's color:{}", color);
        executeInBackground(() -> wrappedElement.updateColor(color));
    }
}
