package org.example.demo.qt.port.ui;

import lombok.SneakyThrows;
import org.example.demo.api.graphics.color.Color;
import org.example.demo.api.graphics.color.Colors;
import org.example.demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.model.metadata.ShapeParam;
import org.example.demo.core.port.ui.PropertiesPanelView;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

import static java.lang.String.valueOf;

/**
 * Qt's realization of {@link PropertiesPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtPropertiesPanelView extends PropertiesPanelView implements QtMemoryView {
    //<editor-fold desc="ABI method's names">
    private static final String NATIVE_PROPERTIES_PANEL_NAME = "ui_properties_panel_get";
    private static final String NATIVE_SET_PANEL_ENABLED_NAME = "set_properties_panel_enabled";

    private static final String NATIVE_X_NAME = "x";
    private static final String NATIVE_Y_NAME = "y";
    private static final String NATIVE_WIDTH_NAME = "width";
    private static final String NATIVE_HEIGHT_NAME = "height";
    private static final String NATIVE_LAYER_NAME = "layer";

    private static final String NATIVE_UPDATE_X_NAME = "ui_set_x";
    private static final String NATIVE_UPDATE_Y_NAME = "ui_set_y";
    private static final String NATIVE_UPDATE_WIDTH_NAME = "ui_set_width";
    private static final String NATIVE_UPDATE_HEIGHT_NAME = "ui_set_height";
    private static final String NATIVE_UPDATE_LAYER_NAME = "ui_set_layer";
    private static final String NATIVE_CLEAR_AND_DISABLE_NAME = "clear_and_disable";

    private static final String NATIVE_UPDATE_X_CALLBACK_NAME = "set_update_x_callback";
    private static final String NATIVE_UPDATE_Y_CALLBACK_NAME = "set_update_y_callback";
    private static final String NATIVE_UPDATE_WIDTH_CALLBACK_NAME = "set_update_width_callback";
    private static final String NATIVE_UPDATE_HEIGHT_CALLBACK_NAME = "set_update_height_callback";
    private static final String NATIVE_UPDATE_LAYER_CALLBACK_NAME = "set_update_layer_callback";
    private static final String NATIVE_UPDATE_COLOR_CALLBACK_NAME = "set_update_color_callback";
    //</editor-fold>

    //<editor-fold desc="Native methods">
    private MethodHandle getQtRefHandle;
    private MethodHandle panelEnabledHandle;

    private MethodHandle updateXHandle;
    private MethodHandle updateYHandle;
    private MethodHandle updateWidthHandle;
    private MethodHandle updateHeightHandle;
    private MethodHandle updateLayerHandle;

    private MethodHandle clearAndDisableHandle;
    //</editor-fold>

    //<editor-fold desc="Callbacks for native methods">
    private MemorySegment updateXSegment;
    private MemorySegment updateYSegment;
    private MemorySegment updateWidthSegment;
    private MemorySegment updateHeightSegment;
    private MemorySegment updateLayerSegment;
    private MemorySegment updateColorSegment;
    //</editor-fold>

    public QtPropertiesPanelView(
            final PropertiesAdapter propertyUpdatable,
            final UIState uiState) {
        super(propertyUpdatable, uiState);
    }

    @Override
    public void initialize() throws Throwable {
        this.getQtRefHandle =
                findNative(
                        NATIVE_PROPERTIES_PANEL_NAME,
                        FunctionDescriptor.of(ValueLayout.ADDRESS)
                );

        this.panelEnabledHandle =
                findNative(
                        NATIVE_SET_PANEL_ENABLED_NAME,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_BOOLEAN
                        ));


        this.updateXHandle = findUpdateMethod(NATIVE_UPDATE_X_NAME);
        this.updateYHandle = findUpdateMethod(NATIVE_UPDATE_Y_NAME);
        this.updateWidthHandle = findUpdateMethod(NATIVE_UPDATE_WIDTH_NAME);
        this.updateHeightHandle = findUpdateMethod(NATIVE_UPDATE_HEIGHT_NAME);
        this.updateLayerHandle = findUpdateMethod(NATIVE_UPDATE_LAYER_NAME);

        this.clearAndDisableHandle = findUpdateMethod(NATIVE_CLEAR_AND_DISABLE_NAME);

        this.updateXSegment = createBoundSegment("updateX", NATIVE_UPDATE_X_CALLBACK_NAME);
        this.updateYSegment = createBoundSegment("updateY", NATIVE_UPDATE_Y_CALLBACK_NAME);
        this.updateWidthSegment = createBoundSegment("updateWidth", NATIVE_UPDATE_WIDTH_CALLBACK_NAME);
        this.updateHeightSegment = createBoundSegment("updateHeight", NATIVE_UPDATE_HEIGHT_CALLBACK_NAME);
        this.updateLayerSegment = createBoundSegment("updateLayer", NATIVE_UPDATE_LAYER_CALLBACK_NAME);
        this.updateLayerSegment = createBoundSegment("updateColor", NATIVE_UPDATE_COLOR_CALLBACK_NAME);
    }

    private MethodHandle findUpdateMethod(final String nativeName) {
        return findNative(
                nativeName,
                FunctionDescriptor.ofVoid(
                        ValueLayout.ADDRESS,
                        ValueLayout.ADDRESS
                ));
    }

    private MemorySegment createBoundSegment(
            final String javaName,
            final String nativeName)
            throws Throwable {

        return bindMethodToNative(
                javaName,
                void.class,
                new Class[]{ MemorySegment.class },
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
                NATIVE_PROPERTIES_PANEL_NAME,
                nativeName
        );
    }

    @Override
    @SneakyThrows
    protected void setPanelDisabled(final boolean isDisabled) {
        panelEnabledHandle.invoke(getQtRefHandle.invoke(), !isDisabled);
    }

    @Override
    protected void clearPanel() {
        clearAndDisable(NATIVE_X_NAME);
        clearAndDisable(NATIVE_Y_NAME);
        clearAndDisable(NATIVE_WIDTH_NAME);
        clearAndDisable(NATIVE_HEIGHT_NAME);
        clearAndDisable(NATIVE_LAYER_NAME);

        /*colorField.setValue(null);
        colorField.setDisable(true);*/
    }

    @Override
    @SneakyThrows
    protected void setUpField(final ShapeParam param, final Number value) {
        if (param == ShapeParam.COLOR) {
/*            if (value != null) {
                colorField.setValue(QtColorUtils.convert((Integer) value));
                colorField.setDisable(false);
            } else {
                colorField.setValue(null);
                colorField.setDisable(true);
            }*/
            return;
        }

        switch (param) {
            case X: updateField(updateXHandle, value, NATIVE_X_NAME); break;
            case Y: updateField(updateYHandle, value, NATIVE_Y_NAME); break;
            case WIDTH: updateField(updateWidthHandle, value, NATIVE_WIDTH_NAME); break;
            case HEIGHT: updateField(updateHeightHandle, value, NATIVE_HEIGHT_NAME); break;
            case PRIORITY: updateField(updateLayerHandle, value, NATIVE_LAYER_NAME); break;
            default: throw new IllegalStateException("Unexpected value: " + param);
        };
    }

    @SneakyThrows
    private void updateField(
            final MethodHandle handle,
            final Number value,
            final String id) {

        if (value != null) {
            try (final Arena arena = Arena.ofConfined()) {
                final MemorySegment utf8 = arena.allocateUtf8String(valueOf(value));
                handle.invoke(getQtRefHandle.invoke(), utf8);
            }
        }
        else {
            clearAndDisable(id);
        }
    }

    @SneakyThrows
    private void clearAndDisable(final String id) {
        try (final Arena arena = Arena.ofConfined()) {
            final MemorySegment utf8 = arena.allocateUtf8String(id);
            clearAndDisableHandle.invoke(getQtRefHandle.invoke(), utf8);
        }
    }

    public void updateX(final MemorySegment segment) {
        final MemorySegment sizedSegment = segment.reinterpret(Long.MAX_VALUE);
        updateX(sizedSegment.getUtf8String(0));
    }

    public void updateY(final MemorySegment segment) {
        final MemorySegment sizedSegment = segment.reinterpret(Long.MAX_VALUE);
        updateY(sizedSegment.getUtf8String(0));
    }

    public void updateWidth(final MemorySegment segment) {
        final MemorySegment sizedSegment = segment.reinterpret(Long.MAX_VALUE);
        updateWidth(sizedSegment.getUtf8String(0));
    }

    public void updateHeight(final MemorySegment segment) {
        final MemorySegment sizedSegment = segment.reinterpret(Long.MAX_VALUE);
        updateHeight(sizedSegment.getUtf8String(0));
    }

    public void updateLayer(final MemorySegment segment) {
        final MemorySegment sizedSegment = segment.reinterpret(Long.MAX_VALUE);
        updateLayer(sizedSegment.getUtf8String(0));
    }

    public void updateColor(final MemorySegment segment) {
        final MemorySegment sizedSegment = segment.reinterpret(Long.MAX_VALUE);
        updateColor(sizedSegment.getUtf8String(0));
    }
}
