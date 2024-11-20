package org.example.astero_demo.port.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.property.PropertyPanelUpdatable;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;
import org.example.astero_demo.util.ColorUtils;

import static org.example.astero_demo.adapter.model.metadata.ParamInfo.create;

public class PropertyPanelView implements UpdatableView {
    public TextField xField;
    public TextField yField;
    public TextField widthField;
    public TextField heightField;
    public TextField layerField;
    public ColorPicker colorField;

    private final PropertyPanelUpdatable propertyUpdatable;
    private final UIState uiState;

    public PropertyPanelView(final PropertyPanelUpdatable propertyUpdatable, final UIState uiState) {
        this.propertyUpdatable = propertyUpdatable;
        this.uiState = uiState;
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

    public void updateX(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            propertyUpdatable.updateX(xField.getText());
        }
    }

    public void updateY(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            propertyUpdatable.updateX(yField.getText());
        }
    }

    public void updateWidth(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            propertyUpdatable.updateX(widthField.getText());
        }
    }

    public void updateHeight(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            propertyUpdatable.updateX(heightField.getText());
        }
    }

    public void updateLayer(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            propertyUpdatable.updateX(layerField.getText());
        }
    }

    public void updateColor(final ActionEvent event) {
        final Color selectedColor = colorField.getValue();
        propertyUpdatable.updateColor(String.valueOf(ColorUtils.convert(selectedColor)));
    }
}
