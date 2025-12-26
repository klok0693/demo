package org.example.demo.integration;

import org.example.demo.core.adapter.keyboard.EditorOperationAdapter;
import org.example.demo.core.adapter.keyboard.OperationAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.controller.keyboard.KeyboardController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

/**
 * Test {@link OperationAdapter}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class OperationAdapterTest {
    private KeyboardController controller;
    private UIState state;
    private OperationAdapter adapter;

    @BeforeEach
    void setUp() {
        this.controller = Mockito.mock(KeyboardController.class);
        this.state = Mockito.mock(UIState.class);
        this.adapter = new EditorOperationAdapter(controller, state);
    }

    @Test
    @DisplayName("Delete operation works correctly")
    void testDeleteOperation() {
        // adapter not perform any actions if there is no selected shape
        Mockito.when(state.hasSelectedId()).thenReturn(false);
        adapter.handleDelete();

        Mockito.verify(state, Mockito.times(1)).hasSelectedId();
        Mockito.verifyNoInteractions(controller);

        // adapter call for logic processor if selected shape exist
        Mockito.when(state.hasSelectedId()).thenReturn(true);
        adapter.handleDelete();

        Mockito.verify(state, Mockito.times(2)).hasSelectedId();
        Mockito.verify(controller, Mockito.times(1)).removeShape(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("Undo operation works correctly")
    void testUndoOperation() {
        // adapter call for logic processor in any situation
        adapter.handleUndo();

        Mockito.verify(controller, Mockito.times(1)).undoLastOperation();
        Mockito.verifyNoInteractions(state);
    }

    @Test
    @DisplayName("Copy operation works correctly")
    void testCopyOperation() {
        // adapter not perform any actions if there is no selected shape
        Mockito.when(state.hasSelectedId()).thenReturn(false);
        adapter.handleCopy();

        Mockito.verify(state, Mockito.times(1)).hasSelectedId();
        Mockito.verifyNoInteractions(controller);

        // adapter call for parent adapter if selected shape exist
        Mockito.when(state.hasSelectedId()).thenReturn(true);
        adapter.handleCopy();

        Mockito.verify(state, Mockito.times(2)).hasSelectedId();
        Mockito.verify(controller, Mockito.times(1)).copy(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("Paste operation works correctly")
    void testPasteOperation() {
        // adapter not perform any actions if there is no selected shape
        adapter.handlePaste();

        Mockito.verify(controller, Mockito.times(1)).paste();
        Mockito.verifyNoInteractions(state);
    }

    @Test
    @DisplayName("Cut operation works correctly")
    void testCutOperation() {
        Mockito.when(state.hasSelectedId()).thenReturn(false);
        adapter.handleCut();

        Mockito.verify(state, Mockito.times(1)).hasSelectedId();
        Mockito.verifyNoInteractions(controller);

        // adapter call for parent adapter if selected shape exist
        Mockito.when(state.hasSelectedId()).thenReturn(true);
        adapter.handleCut();

        Mockito.verify(state, Mockito.times(2)).hasSelectedId();
        Mockito.verify(controller, Mockito.times(1)).cut(ArgumentMatchers.anyInt());
    }
}
