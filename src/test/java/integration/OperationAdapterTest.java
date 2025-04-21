package integration;

import org.example.astero_demo.adapter.keyboard.EditorOperationAdapter;
import org.example.astero_demo.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.logic.ShapeProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Test {@link OperationAdapter}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class OperationAdapterTest {
    private ShapeProcessor processor;
    private UIState state;
    private ParentAdapter parentAdapter;
    private OperationAdapter adapter;

    @BeforeEach
    void setUp() {
        this.processor = mock(ShapeProcessor.class);
        this.state = mock(UIState.class);
        this.parentAdapter = mock(ParentAdapter.class);
        this.adapter = new EditorOperationAdapter(processor, state, parentAdapter);
    }

    @Test
    @DisplayName("Delete operation works correctly")
    void testDeleteOperation() {
        // adapter not perform any actions if there is no selected shape
        when(state.hasSelectedId()).thenReturn(false);
        adapter.handleDelete();

        verify(state, times(1)).hasSelectedId();
        verifyNoInteractions(processor, parentAdapter);

        // adapter call for logic processor if selected shape exist
        when(state.hasSelectedId()).thenReturn(true);
        adapter.handleDelete();

        verify(state, times(2)).hasSelectedId();
        verify(processor, times(1)).removeShape(anyInt());
        verifyNoInteractions(parentAdapter);
    }

    @Test
    @DisplayName("Undo operation works correctly")
    void testUndoOperation() {
        // adapter call for logic processor in any situation
        adapter.handleUndo();

        verify(processor, times(1)).undoLastOperation();
        verifyNoInteractions(state, parentAdapter);
    }

    @Test
    @DisplayName("Copy operation works correctly")
    void testCopyOperation() {
        // adapter not perform any actions if there is no selected shape
        when(state.hasSelectedId()).thenReturn(false);
        adapter.handleCopy();

        verify(state, times(1)).hasSelectedId();
        verifyNoInteractions(processor, parentAdapter);

        // adapter call for parent adapter if selected shape exist
        when(state.hasSelectedId()).thenReturn(true);
        adapter.handleCopy();

        verify(state, times(2)).hasSelectedId();
        verify(parentAdapter, times(1)).processEvent(any());
        verifyNoInteractions(processor);
    }

    @Test
    @DisplayName("Paste operation works correctly")
    void testPasteOperation() {
        // adapter not perform any actions if there is no selected shape
        when(state.hasCopy()).thenReturn(false);
        adapter.handlePaste();

        verify(state, times(1)).hasCopy();
        verifyNoInteractions(processor, parentAdapter);

        // adapter call for parent adapter if selected shape exist
        when(state.hasCopy()).thenReturn(true);
        adapter.handlePaste();

        verify(state, times(2)).hasCopy();
        verify(parentAdapter, times(1)).processEvent(any());
        verifyNoInteractions(processor);
    }
}
