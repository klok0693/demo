package org.example.demo.fx.port.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.model.metadata.ShapeParam;
import org.example.demo.core.port.ui.PropertiesPanelView;
import org.example.demo.fx.util.ColorUtils;

/**
 * JavaFX's realization of {@link PropertiesPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxPropertiesPanelView extends PropertiesPanelView {
    @FXML
    public VBox propertyRoot;
    @FXML
    public TextField xField;
    @FXML
    public TextField yField;
    @FXML
    public TextField widthField;
    @FXML
    public TextField heightField;
    @FXML
    public TextField layerField;
    @FXML
    public ColorPicker colorField;

    public FxPropertiesPanelView(
            final PropertiesAdapter propertyUpdatable,
            final UIState uiState) {
        super(propertyUpdatable, uiState);
    }

    @Override
    protected void setPanelDisabled(final boolean isDisabled) {
        propertyRoot.setDisable(isDisabled);
    }

    @Override
    protected void clearPanel() {
        clearAndDisable(xField);
        clearAndDisable(yField);
        clearAndDisable(widthField);
        clearAndDisable(heightField);
        clearAndDisable(layerField);

        colorField.setValue(null);
        colorField.setDisable(true);
    }

    @Override
    protected void setUpField(final ShapeParam param, final Number value) {
        if (param == ShapeParam.COLOR) {
            if (value != null) {
                colorField.setValue(ColorUtils.convert((Integer) value));
                colorField.setDisable(false);
            } else {
                colorField.setValue(null);
                colorField.setDisable(true);
            }
            return;
        }

        final TextField field = switch (param) {
            case X -> xField;
            case Y -> yField;
            case WIDTH -> widthField;
            case HEIGHT -> heightField;
            case PRIORITY -> layerField;
            default -> throw new IllegalStateException("Unexpected value: " + param);
        };

        if (value != null) {
            field.setText(String.valueOf(value));
            field.setDisable(false);
        }
        else {
            clearAndDisable(field);
        }
    }

    private static void clearAndDisable(final TextInputControl field) {
        field.clear();
        field.setDisable(true);
    }

    public void updateX(final ActionEvent event) {
        updateX(xField.getText());
    }

    public void updateY(final ActionEvent event) {
        updateY(yField.getText());
    }

    public void updateWidth(final ActionEvent event) {
        updateWidth(widthField.getText());
    }

    public void updateHeight(final ActionEvent event) {
        updateHeight(heightField.getText());
    }

    public void updateLayer(final ActionEvent event) {
        updateLayer(layerField.getText());
    }

    public void updateColor(final ActionEvent event) {
        final Color selectedColor = colorField.getValue();
        updateColor(String.valueOf(ColorUtils.convert(selectedColor)));
    }
}
