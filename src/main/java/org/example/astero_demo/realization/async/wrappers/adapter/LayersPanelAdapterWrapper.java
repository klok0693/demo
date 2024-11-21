package org.example.astero_demo.realization.async.wrappers.adapter;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersPanelAdapter;
import org.example.astero_demo.realization.async.AppExecutor;

import static org.example.astero_demo.realization.logging.MarkerStorage.USER_INPUT_MARKER;

/**
 * Wrapper class that adapts interactions with a layers panel
 * by delegating to a wrapped {@link LayersPanelAdapter} instance.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class LayersPanelAdapterWrapper extends UpdatableAdapterAsyncWrapper<LayersAdapter> implements LayersAdapter {

    @Inject
    protected LayersPanelAdapterWrapper(final AppExecutor executor, final LayersPanelAdapter wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public boolean hasInjectedField(final String fieldName) {
        return super.hasInjectedField(fieldName) /*|| "layersRoot".equals(fieldName)*/;
    }

    @Override
    public void selectShape(final String id) {
        log.debug(USER_INPUT_MARKER, "Select shape with id:{}", id);
        wrappedElement.selectShape(id);
    }
}
