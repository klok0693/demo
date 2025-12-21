package org.example.astero_demo.swing.port.ui.properties;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.core.adapter.ui.property.PropertiesView;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.swing.util.ColorUtils;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class SwingPropertiesPanelUI extends Box implements PropertiesPanelUI {

    private final JTextField xField;
    private final JTextField yField;
    private final JTextField widthField;
    private final JTextField heightField;
    private final JTextField layerField;
    private final JButton colorButton;

    public SwingPropertiesPanelUI(final PropertiesView panelView) {
        super(BoxLayout.Y_AXIS);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //setBackground(Color.green);
        //setOpaque(true);
        setEnabled(false);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(Color.BLACK)));

        setPreferredSize(new Dimension(240, 380));
        setAlignmentY(JComponent.TOP_ALIGNMENT);


        /**
         * *---------------------------------------------------------------------+
         * |  xLabel       |  xTextField      |  yLabel       |  yTextField      |
         * +---------------------------------------------------------------------+
         * |  widthLabel   |  widthTextField  |  heightLabel  |  heightTextField |
         * +---------------------------------------------------------------------+
         * |  layerLabel   |  layerTextField  |     EMPTY     |      EMPTY       |
         * +---------------------------------------------------------------------+
         * |  colorPicker  |      EMPTY       |     EMPTY     |      EMPTY       |
         * +---------------------------------------------------------------------+
         */
        final JPanel propertiesGrid = new JPanel();
        propertiesGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, propertiesGrid.getPreferredSize().height));

        final GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {0.22, 0.28, 0.22, 0.28};
        propertiesGrid.setLayout(gbl);

        final GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 10, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        final BiConsumer<Integer, Integer> setGrid = (row, col) -> {
            c.gridy = row;
            c.gridx = col;
        };

        // ---- Row 0 ----
        setGrid.accept(0, 0);
        c.anchor = GridBagConstraints.EAST;
        propertiesGrid.add(createLabel("x"), c.clone());

        setGrid.accept(0, 1);
        c.anchor = GridBagConstraints.WEST;
        this.xField = createTextField();
        propertiesGrid.add(xField, c.clone());

        setGrid.accept(0, 2);
        c.anchor = GridBagConstraints.EAST;
        propertiesGrid.add(createLabel("y"), c.clone());

        setGrid.accept(0, 3);
        c.anchor = GridBagConstraints.WEST;
        this.yField = createTextField();
        propertiesGrid.add(yField, c.clone());

        // ---- Row 1 ----
        setGrid.accept(1, 0);
        c.anchor = GridBagConstraints.EAST;
        propertiesGrid.add(createLabel("width"), c.clone());

        setGrid.accept(1, 1);
        c.anchor = GridBagConstraints.WEST;
        this.widthField = createTextField();
        propertiesGrid.add(widthField, c.clone());

        setGrid.accept(1, 2);
        final GridBagConstraints heightClone = (GridBagConstraints) c.clone();
        heightClone.anchor = GridBagConstraints.EAST;
        heightClone.fill = GridBagConstraints.NONE;
        propertiesGrid.add(createLabel("height"), heightClone);

        setGrid.accept(1, 3);
        c.anchor = GridBagConstraints.WEST;
        this.heightField = createTextField();
        propertiesGrid.add(heightField, c.clone());

        // ---- Row 2 ----
        setGrid.accept(2, 0);
        c.anchor = GridBagConstraints.EAST;
        propertiesGrid.add(createLabel("layer"), c.clone());

        setGrid.accept(2, 1);
        c.anchor = GridBagConstraints.WEST;
        this.layerField = createTextField();
        propertiesGrid.add(layerField, c.clone());

        // ---- Row 3 ----
        setGrid.accept(3, 0);
        c.anchor = GridBagConstraints.EAST;
        propertiesGrid.add(createLabel("color"), c.clone());

        setGrid.accept(3, 1);
        c.anchor = GridBagConstraints.WEST;
        this.colorButton = getButton(propertiesGrid);
        propertiesGrid.add(colorButton, c);

        add(propertiesGrid);
        add(Box.createVerticalGlue());

        this.xField.addActionListener(e -> panelView.updateX(xField.getText()));
        this.yField.addActionListener(e -> panelView.updateY(yField.getText()));
        this.widthField.addActionListener(e -> panelView.updateWidth(widthField.getText()));
        this.heightField.addActionListener(e -> panelView.updateHeight(heightField.getText()));
        this.layerField.addActionListener(e -> panelView.updateLayer(layerField.getText()));
        colorButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(propertiesGrid, "Choose Color", Color.WHITE);
            if (selectedColor != null) {
                panelView.updateColor(String.valueOf(ColorUtils.convert(selectedColor)));
            }
        });
    }

    private static JLabel createLabel(final String text) {
        final JLabel label = new JLabel(text + ':');
        setupSize(label);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return label;
    }

    private static JTextField createTextField() {
        final JTextField field = new JTextField();
        setupSize(field);
        field.setColumns(10);
        field.setEnabled(false);
        return field;
    }

    private static JButton getButton(final JPanel propertiesGrid) {
        final JButton colorButton = new JButton("Color");
        colorButton.setFont(colorButton.getFont().deriveFont(12.0f));
        colorButton.setMargin(new Insets(0, 0, 0, 0));
        setupSize(colorButton);
        colorButton.setEnabled(false);
        return colorButton;
    }

    private static void setupSize(final JComponent component) {
        // To let the GridBag set up the width of the element
        component.setMinimumSize(new Dimension(0, component.getPreferredSize().height));
        component.setPreferredSize(new Dimension(0, component.getPreferredSize().height));
    }

    @Override
    public void setDisable(final boolean isDisable) {
        setEnabled(!isDisable);
    }

    @Override
    public void clearPanel() {
        cleanAndDisable(xField);
        cleanAndDisable(yField);
        cleanAndDisable(widthField);
        cleanAndDisable(heightField);
        cleanAndDisable(layerField);
        colorButton.setEnabled(false);
    }

    private void cleanAndDisable(final JTextField textField) {
        textField.setText(StringUtils.EMPTY);
        textField.setEnabled(false);
    }

    @Override
    public void setUpField(final ShapeParam param, final Number value) {
        if (param == ShapeParam.COLOR) {
            colorButton.setEnabled(value != null);
        }

        final JTextField field = switch (param) {
            case X -> xField;
            case Y -> yField;
            case WIDTH -> widthField;
            case HEIGHT -> heightField;
            case PRIORITY -> layerField;
            default -> null; //throw new IllegalStateException("Unexpected value: " + param);
        };

        if (value != null && field != null) {
            field.setText(String.valueOf(value));
            field.setEnabled(true);
        }
        else if (field != null) {
            cleanAndDisable(field);
        }
    }
}
