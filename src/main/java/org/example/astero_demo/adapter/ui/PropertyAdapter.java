package org.example.astero_demo.adapter.ui;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PropertyAdapter extends LeafAdapter {

    public TextField xField;
    public TextField yField;
    public TextField widthField;
    public TextField heightField;
    public TextField layerField;
    public TextField colorField;

    public PropertyAdapter(final ViewController controller, final UIState uiState) {
        super(controller, uiState);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @Override
    public void update() {
        setUpField(xField, uiState.getSelectedX());
        setUpField(yField, uiState.getSelectedY());
        setUpField(widthField, uiState.getSelectedWidth());
        setUpField(heightField, uiState.getSelectedHeight());
        setUpField(layerField, uiState.getSelectedLayer());
    }

    private static void setUpField(final TextInputControl field, final Number number) {
        if (number != null) {
            field.setText(String.valueOf(number));
            field.setDisable(false);
        }
        else {
            field.clear();
            field.setDisable(true);
        }
    }

    public void updateX(final KeyEvent keyEvent) {
        updateField(keyEvent, ShapeParam.X, xField);
    }

    public void updateY(final KeyEvent keyEvent) {
        updateField(keyEvent, ShapeParam.Y, yField);
    }

    public void updateWidth(final KeyEvent keyEvent) {
        updateField(keyEvent, ShapeParam.WIDTH, widthField);
    }

    public void updateHeight(final KeyEvent keyEvent) {
        updateField(keyEvent, ShapeParam.HEIGHT, heightField);
    }

    public void updateLayer(final KeyEvent keyEvent) {
        updateField(keyEvent, ShapeParam.LAYER, layerField);
    }

    private void updateField(final KeyEvent e, final ShapeParam param, final TextInputControl field) {
        if (e.getCode() == KeyCode.ENTER) {
            controller.process(new ModifyShapeEvent(
                    uiState.getSelectedShapeId(), param, field.getText()));
        }
    }
}
