package org.example.astero_demo.core.port.ui.canvas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.core.adapter.ui.state.mode.InsertModeSwitchable;
import org.example.astero_demo.core.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.core.port.ui.canvas.shape.ShapeLayer;
import org.example.astero_demo.core.port.ui.canvas.tool.ToolLayer;

/*import java.awt.*;*/
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Represents a canvas view that displays shapes and provide tools<p>
 * to perform operations with its content. All elements are drawn on<p>
 * a separate layers: first background, next shapes and tools on top of it<p>
 * Also delegate mouse events to its child elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ShapeCanvasView implements CanvasView {
    protected final UIState uiState;
    protected final ModelState modelState;
    protected final CanvasAdapter adapter;

    public ShapeCanvasView(
            final UIState uiState,
            final ModelState modelState,
            final CanvasAdapter adapter) {
        this.modelState = modelState;
        this.uiState = uiState;
        this.adapter = adapter;
    }

    protected abstract void redraw();
}
