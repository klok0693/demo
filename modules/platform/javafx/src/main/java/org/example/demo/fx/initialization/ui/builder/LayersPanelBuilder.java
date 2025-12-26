package org.example.demo.fx.initialization.ui.builder;

import com.google.inject.Inject;
import javafx.beans.DefaultProperty;
import javafx.util.Builder;
import org.example.demo.core.port.ui.LayersPanelView;

import java.util.HashMap;

/**
 * {@link Builder} for {@link LayersPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@DefaultProperty("children")
public class LayersPanelBuilder extends HashMap<Object, Object> implements Builder<LayersPanelView>  {
    private final LayersPanelView panel;

    @Inject
    public LayersPanelBuilder(final LayersPanelView panel) {
        this.panel = panel;
    }

    @Override
    public LayersPanelView build() {
        return panel;
    }
}
