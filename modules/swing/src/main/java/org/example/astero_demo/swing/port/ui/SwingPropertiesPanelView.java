package org.example.astero_demo.swing.port.ui;

import org.example.astero_demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;

/**
 * JavaFX's realization of {@link PropertiesPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingPropertiesPanelView extends PropertiesPanelView {
/*    public VBox propertyRoot;
    public TextField xField;
    public TextField yField;
    public TextField widthField;
    public TextField heightField;
    public TextField layerField;
    public ColorPicker colorField;*/

    public SwingPropertiesPanelView(
            final PropertiesAdapter propertyUpdatable,
            final UIState uiState) {
        super(propertyUpdatable, uiState);
    }

    @Override
    protected void setPanelDisabled(final boolean isDisabled) {
        //propertyRoot.setDisable(isDisabled);
    }

    @Override
    protected void clearPanel() {
/*        clearAndDisable(xField);
        clearAndDisable(yField);
        clearAndDisable(widthField);
        clearAndDisable(heightField);
        clearAndDisable(layerField);

        colorField.setValue(null);
        colorField.setDisable(true);*/
    }

    @Override
    protected void setUpField(final ShapeParam param, final Number value) {
/*        if (param == ShapeParam.COLOR) {
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
        }*/
    }

/*    private static void clearAndDisable(final TextInputControl field) {
      *//*  field.clear();
        field.setDisable(true);*//*
    }

    public void updateX(final KeyEvent event) {
        if (needToHandle(event)) {
            updateField(ShapeParam.X, xField.getText());
        }
    }

    public void updateY(final KeyEvent event) {
        if (needToHandle(event)) {
            updateField(ShapeParam.Y, yField.getText());
        }
    }

    public void updateWidth(final KeyEvent event) {
        if (needToHandle(event)) {
            updateField(ShapeParam.WIDTH, widthField.getText());
        }
    }

    public void updateHeight(final KeyEvent event) {
        if (needToHandle(event)) {
            updateField(ShapeParam.HEIGHT, heightField.getText());
        }
    }

    public void updateLayer(final KeyEvent event) {
        if (needToHandle(event)) {
            updateField(ShapeParam.PRIORITY, layerField.getText());
        }
    }

    public void updateColor(final ActionEvent event) {
        final Color selectedColor = colorField.getValue();
        updateField(ShapeParam.COLOR, String.valueOf(ColorUtils.convert(selectedColor)));
    }

    private static boolean needToHandle(final KeyEvent event) {
        return event.getCode() == KeyCode.ENTER;
    }*/
}
