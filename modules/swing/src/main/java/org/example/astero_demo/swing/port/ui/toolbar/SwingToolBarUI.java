package org.example.astero_demo.swing.port.ui.toolbar;

import org.example.astero_demo.core.port.ui.ToolBarPanelView;

import javax.swing.*;

public class SwingToolBarUI extends Box implements ToolBarUI {
    private final JToggleButton insertRectBtn;
    private final JToggleButton insertCycleBtn;
    private final JToggleButton undoBtn;
    private final JToggleButton deleteBtn;

    public SwingToolBarUI(final ToolBarPanelView view) {
        super(BoxLayout.X_AXIS);

        this.insertRectBtn = new JToggleButton("Rect");
        this.insertRectBtn.addActionListener(e -> {
            view.onInsertRectAction();
        });

        this.insertCycleBtn = new JToggleButton("Cycle");
        this.insertCycleBtn.addActionListener(e -> {
            view.onInsertCycleAction();
        });

        final ButtonGroup insertGroup = new ButtonGroup();
        insertGroup.add(insertRectBtn);
        insertGroup.add(insertCycleBtn);

        final Box insertBtnBox = Box.createHorizontalBox();
        insertBtnBox.add(insertRectBtn);
        insertBtnBox.add(insertCycleBtn);

        this.undoBtn = new JToggleButton("Undo");
        this.undoBtn.addActionListener(e -> {
            view.onUndoAction();
        });

        this.deleteBtn = new JToggleButton("Delete");
        this.deleteBtn.addActionListener(e -> {
            view.onDeleteAction();
        });

        Box eastBtnBox = Box.createHorizontalBox();
        eastBtnBox.add(undoBtn);
        eastBtnBox.add(deleteBtn);

        add(insertBtnBox);
        add(eastBtnBox);
    }

    @Override
    public void setDeleteBtnDisabled(final boolean isDisabled) {
        deleteBtn.setEnabled(!isDisabled);
    };

    @Override
    public void setInsertRectBtnSelected(final boolean setSelected) {
        insertRectBtn.setSelected(setSelected);
    };

    @Override
    public void setInsertCycleBtnSelected(final boolean setSelected) {
        insertCycleBtn.setSelected(setSelected);
    };
}
