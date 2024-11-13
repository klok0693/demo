package org.example.astero_demo.adapter.ui.state;

import lombok.Getter;
import lombok.Setter;

public enum UIStateHolder implements MutableUIState {
    INSTANCE;

    @Getter
    private boolean isInInsertMode = false;

    @Override
    public void setIsInInsertMode(final boolean isInInsertMode) {
        this.isInInsertMode = isInInsertMode;
    }
}
