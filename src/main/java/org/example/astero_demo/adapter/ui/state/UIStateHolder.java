package org.example.astero_demo.adapter.ui.state;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public enum UIStateHolder implements MutableUIState {
    INSTANCE;

    @Getter
    private boolean isInInsertMode = false;

    private List<Integer> selectedShapeIds = new LinkedList<>();

    @Override
    public void setIsInInsertMode(final boolean isInInsertMode) {
        this.isInInsertMode = isInInsertMode;
    }

    @Override
    public int getSelectedShapeId() {
        return Optional.ofNullable(selectedShapeIds.getFirst()).orElse(-1);
    }

    @Override
    public boolean hasSelectedId() {
        return !selectedShapeIds.isEmpty();
    }


    @Override
    public void setSelectShape(final int shapeId) {
        removeSelection();
        selectedShapeIds.add(shapeId);
    }

    @Override
    public void removeSelection() {
        selectedShapeIds.clear();
    }
}
