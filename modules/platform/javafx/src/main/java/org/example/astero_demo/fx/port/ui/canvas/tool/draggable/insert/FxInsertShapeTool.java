package org.example.astero_demo.fx.port.ui.canvas.tool.draggable.insert;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert.InsertShapeTool;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;
import org.example.astero_demo.fx.port.ui.graphics.FxPainter;

/**
 * JavaFX's realization of {@link InsertShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxInsertShapeTool extends InsertShapeTool<FxPainter> implements FxCanvasElement {

    public FxInsertShapeTool(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
    }

    @Override
    public void draw(final FxPainter gc) {
        save(gc);
        super.draw(gc);
        restore(gc);
    }
}
