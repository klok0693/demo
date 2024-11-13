package org.example.astero_demo.port.ui;

import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;
import lombok.Setter;
import org.example.astero_demo.adapter.ui.state.UIState;

public class RootView extends BorderPane {
    @Setter
    private UIState uiState;

    public void update() {
        setCursor(uiState.isInInsertMode() ? Cursor.CROSSHAIR : Cursor.DEFAULT);
    }
}
