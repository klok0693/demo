package org.example.demo.qt.port.ui;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.demo.core.port.ui.ToolBarPanelView;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

/**
 * JavaFX's realization of {@link ToolBarPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtToolBarView extends ToolBarPanelView implements QtMemoryView {
    private MemorySegment onInsertRectSegment;
    private MemorySegment onInsertCycleSegment;
    private MemorySegment onUndoSegment;
    private MemorySegment onDeleteSegment;

    private MethodHandle deleteBtnDisabledHandle;

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

        //this.deleteBtnDisabledHandle = findNative();
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
                "ui_toolbar_get",
                nativeName
        );
    }

    @Override
    protected void setDeleteBtnDisabled(final boolean isDisabled) {
        //deleteBtn.setDisable(isDisabled);
    }

    @Override
    protected void setInsertRectBtnSelected(final boolean setSelected) {
        //insertRectBtn.setSelected(setSelected);
    }

    @Override
    protected void setInsertCycleBtnSelected(final boolean setSelected) {
        //insertCycleBtn.setSelected(setSelected);
    }
}
