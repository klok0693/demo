package org.example.astero_demo.core.port.ui;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitchableView;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Represents the root view of the application, responsible for managing the main View components.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class RootView implements Initializable, ModeSwitchableView {
    protected final UIState uiState;

    protected RootView(final UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void update() {}
}
