package org.example.demo.qt.port.ui;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.demo.core.port.ui.LayersPanelView;
import org.example.demo.core.port.ui.PropertiesPanelView;
import org.example.demo.core.port.ui.RootView;
import org.example.demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.demo.core.port.ui.model.Cursors;

/**
 * JavaFX's realization of {@link RootView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtRootView extends RootView {

    public ToolBarView toolBarRootController;
    public ShapeCanvasView canvasRootController;
    public PropertiesPanelView propertyRootController;
    public LayersPanelView layersRootController;
    //public BorderPane root;

    public QtRootView(final UIState uiState) {
        super(uiState);
    }

    @Override
    protected void setCursor(final Cursors cursor) {
/*        final Cursor fxCursor = switch (cursor) {
            case DEFAULT -> Cursor.DEFAULT;
            case CROSSHAIR -> Cursor.CROSSHAIR;
        };
        root.setCursor(fxCursor);*/
    }
}
