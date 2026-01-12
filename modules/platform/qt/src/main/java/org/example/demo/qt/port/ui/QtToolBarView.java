package org.example.demo.qt.port.ui;

import lombok.SneakyThrows;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.demo.core.port.ui.ToolBarPanelView;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

/**
 * Qt view adapter for {@link ToolBarPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtToolBarView extends ToolBarPanelView implements QtMemoryView {
    //<editor-fold desc="ABI method's names">
    private static final String SET_INSERT_RECT_CALLBACK_NAME = "set_toolbar_insert_rect_callback";
    private static final String SET_INSERT_CYCLE_CALLBACK_NAME = "set_toolbar_insert_cycle_callback";
    private static final String SET_UNDO_CALLBACK_NAME = "set_toolbar_undo_callback";
    private static final String SET_DELETE_CALLBACK_NAME = "set_toolbar_delete_callback";

    private static final String NATIVE_REF_NAME = "ui_toolbar_get";
    private static final String NATIVE_INSERT_RECT_BTN_SELECTED = "set_toolbar_insert_rect_btn_selected";
    private static final String NATIVE_INSERT_CYCLE_BTN_SELECTED = "set_toolbar_insert_cycle_btn_selected";
    private static final String NATIVE_DELETE_BTN_DISABLED = "set_toolbar_delete_btn_disabled";
    //</editor-fold>

    private MemorySegment onInsertRectSegment;
    private MemorySegment onInsertCycleSegment;
    private MemorySegment onUndoSegment;
    private MemorySegment onDeleteSegment;

    private MethodHandle getQtRefHandle;
    private MethodHandle deleteBtnDisabledHandle;
    private MethodHandle insertRectBtnSelectedHandle;
    private MethodHandle insertCycleBtnSelectedHandle;

    public QtToolBarView(
            final UIState uiState,
            final ToolBarAdapter operationProcessor) {
        super(uiState, operationProcessor);
    }

    @Override
    public void initialize() throws Throwable {
        this.onInsertRectSegment =
                createBoundSegment("onInsertRectAction", SET_INSERT_RECT_CALLBACK_NAME);
        this.onInsertCycleSegment =
                createBoundSegment("onInsertCycleAction", SET_INSERT_CYCLE_CALLBACK_NAME);
        this.onUndoSegment =
                createBoundSegment("onUndoAction", SET_UNDO_CALLBACK_NAME);
        this.onDeleteSegment =
                createBoundSegment("onDeleteAction", SET_DELETE_CALLBACK_NAME);

        this.getQtRefHandle =
                findNative(NATIVE_REF_NAME, FunctionDescriptor.of(ValueLayout.ADDRESS));

        this.deleteBtnDisabledHandle =
                findNative(
                        NATIVE_DELETE_BTN_DISABLED,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_BOOLEAN
                        ));

        this.insertRectBtnSelectedHandle =
                findNative(
                        NATIVE_INSERT_RECT_BTN_SELECTED,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_BOOLEAN
                        ));

        this.insertCycleBtnSelectedHandle =
                findNative(
                        NATIVE_INSERT_CYCLE_BTN_SELECTED,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_BOOLEAN
                        ));
    }

    private MemorySegment createBoundSegment(
            final String javaName,
            final String nativeName)
            throws Throwable {

        return bindMethodToNative(
                javaName,
                void.class,
                new Class[]{},
                FunctionDescriptor.ofVoid(),
                NATIVE_REF_NAME,
                nativeName
        );
    }

    @Override
    @SneakyThrows
    protected void setDeleteBtnDisabled(final boolean isDisabled) {
        deleteBtnDisabledHandle.invoke(getQtRefHandle.invoke(), isDisabled);
    }

    @Override
    @SneakyThrows
    protected void setInsertRectBtnSelected(final boolean setSelected) {
        insertRectBtnSelectedHandle.invoke(getQtRefHandle.invoke(), setSelected);
    }

    @Override
    @SneakyThrows
    protected void setInsertCycleBtnSelected(final boolean setSelected) {
        insertCycleBtnSelectedHandle.invoke(getQtRefHandle.invoke(), setSelected);
    }
}
