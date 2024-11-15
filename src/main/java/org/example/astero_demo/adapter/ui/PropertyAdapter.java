package org.example.astero_demo.adapter.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;
import org.example.astero_demo.util.ColorUtils;

import java.net.URL;
import java.util.ResourceBundle;

import static org.example.astero_demo.adapter.model.ParamInfo.create;

public class PropertyAdapter extends LeafAdapter {

    public TextField xField;
    public TextField yField;
    public TextField widthField;
    public TextField heightField;
    public TextField layerField;
    public ColorPicker colorField;

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

        final Integer color = uiState.getSelectedColor();
        if (color != null) {
            colorField.setValue(ColorUtils.convert(color));
            colorField.setDisable(false);
        }
        else {
            colorField.setValue(null);
            colorField.setDisable(true);
        }
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
        updateField(keyEvent, ShapeParam.PRIORITY, layerField);
    }

    private void updateField(final KeyEvent e, final ShapeParam param, final TextInputControl field) {
        if (e.getCode() == KeyCode.ENTER) {
            controller.process(new ModifyShapeEvent(
                    uiState.getSelectedShapeId(), create(param, field.getText())));
        }
    }

    public void updateColor(final ActionEvent event) {
        final Color selectedColor = colorField.getValue();
        controller.process(new ModifyShapeEvent(
                uiState.getSelectedShapeId(), create(ShapeParam.COLOR, String.valueOf(ColorUtils.convert(selectedColor)))));
    }
}
