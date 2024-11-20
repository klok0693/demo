package org.example.astero_demo.adapter.ui.property;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;
import org.example.astero_demo.port.ui.PropertyPanelView;
import org.example.astero_demo.util.ColorUtils;

import java.net.URL;
import java.util.ResourceBundle;

import static org.example.astero_demo.adapter.model.metadata.ParamInfo.create;

public class PropertyPanelAdapter extends LeafAdapter implements PropertyPanelUpdatable {
    private final UpdatableView propertyView;

    public PropertyPanelAdapter(
            final ViewController controller,
            final UIState uiState,
            final UpdatableView propertyView) {
        super(controller, uiState);
        this.propertyView = propertyView;
    }

    @Override
    public void update() {
        propertyView.update();
    }

    @Override
    public void updateX(final String x) {
        updateField(ShapeParam.X, x);
    }

    @Override
    public void updateY(final String y) {
        updateField(ShapeParam.Y, y);
    }

    @Override
    public void updateWidth(final String width) {
        updateField(ShapeParam.WIDTH, width);
    }

    @Override
    public void updateHeight(final String height) {
        updateField(ShapeParam.HEIGHT, height);
    }

    @Override
    public void updateLayer(final String layer) {
        updateField(ShapeParam.PRIORITY, layer);
    }

    @Override
    public void updateColor(final String color) {
        updateField(ShapeParam.COLOR, color);
    }

    private void updateField(final ShapeParam param, final String value) {
        controller.process(new ModifyShapeEvent(uiState.getSelectedShapeId(), create(param, value)));
    }
}
