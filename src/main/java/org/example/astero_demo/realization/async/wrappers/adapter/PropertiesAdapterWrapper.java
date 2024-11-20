package org.example.astero_demo.realization.async.wrappers.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesPanelAdapter;
import org.example.astero_demo.realization.async.AppExecutor;

public class PropertiesAdapterWrapper extends UpdatableAdapterAsyncWrapper<PropertiesAdapter> implements PropertiesAdapter {

    @Inject
    protected PropertiesAdapterWrapper(final AppExecutor executor, final PropertiesPanelAdapter wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public void updateX(final String x) {
        executeInBackground(() -> wrappedElement.updateX(x));
    }

    @Override
    public void updateY(final String y) {
        executeInBackground(() -> wrappedElement.updateY(y));
    }

    @Override
    public void updateWidth(final String width) {
        executeInBackground(() -> wrappedElement.updateWidth(width));
    }

    @Override
    public void updateHeight(final String height) {
        executeInBackground(() -> wrappedElement.updateHeight(height));
    }

    @Override
    public void updateLayer(final String layer) {
        executeInBackground(() -> wrappedElement.updateLayer(layer));
    }

    @Override
    public void updateColor(final String color) {
        executeInBackground(() -> wrappedElement.updateColor(color));
    }
}
