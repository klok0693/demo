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
 * JavaFX's realization of {@link ToolBarPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtToolBarView extends ToolBarPanelView implements QtMemoryView {
    private static final String NATIVE_REF_NAME = "ui_toolbar_get";

    private MemorySegment onInsertRectSegment;
    private MemorySegment onInsertCycleSegment;
    private MemorySegment onUndoSegment;
    private MemorySegment onDeleteSegment;

    /**
     * Java objects do not store native object's references, only
     * MethodHandler, that provide references
     */
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
                createBoundSegment("onInsertRectAction", "setToolBarInsertRectCallback");
        this.onInsertCycleSegment =
                createBoundSegment("onInsertCycleAction", "setToolBarInsertCycleCallback");
        this.onUndoSegment =
                createBoundSegment("onUndoAction", "setToolBarUndoCallback");
        this.onDeleteSegment =
                createBoundSegment("onDeleteAction", "setToolBarDeleteCallback");

        this.getQtRefHandle =
                findNative(NATIVE_REF_NAME, FunctionDescriptor.of(ValueLayout.ADDRESS));

        this.deleteBtnDisabledHandle =
                findNative(
                        "setToolBarDeleteBtnDisabled",
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_BOOLEAN
                        ));

        this.insertRectBtnSelectedHandle =
                findNative(
                        "setToolBarInsertRectBtnSelected",
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_BOOLEAN
                        ));

        this.insertCycleBtnSelectedHandle =
                findNative(
                        "setToolBarInsertCycleBtnSelected",
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
