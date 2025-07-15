package integration;

import org.example.astero_demo.core.adapter.keyboard.EditorOperationAdapter;
import org.example.astero_demo.core.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.core.adapter.ui.ParentAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.logic.ShapeProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

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
        this.processor = Mockito.mock(ShapeProcessor.class);
        this.state = Mockito.mock(UIState.class);
        this.parentAdapter = Mockito.mock(ParentAdapter.class);
        this.adapter = new EditorOperationAdapter(processor, state, parentAdapter);
    }

    @Test
    @DisplayName("Delete operation works correctly")
    void testDeleteOperation() {
        // adapter not perform any actions if there is no selected shape
        Mockito.when(state.hasSelectedId()).thenReturn(false);
        adapter.handleDelete();

        Mockito.verify(state, Mockito.times(1)).hasSelectedId();
        Mockito.verifyNoInteractions(processor, parentAdapter);

        // adapter call for logic processor if selected shape exist
        Mockito.when(state.hasSelectedId()).thenReturn(true);
        adapter.handleDelete();

        Mockito.verify(state, Mockito.times(2)).hasSelectedId();
        Mockito.verify(processor, Mockito.times(1)).removeShape(ArgumentMatchers.anyInt());
        verifyNoInteractions(parentAdapter);
    }

    @Test
    @DisplayName("Undo operation works correctly")
    void testUndoOperation() {
        // adapter call for logic processor in any situation
        adapter.handleUndo();

        Mockito.verify(processor, Mockito.times(1)).undoLastOperation();
        Mockito.verifyNoInteractions(state, parentAdapter);
    }

    @Test
    @DisplayName("Copy operation works correctly")
    void testCopyOperation() {
        // adapter not perform any actions if there is no selected shape
        Mockito.when(state.hasSelectedId()).thenReturn(false);
        adapter.handleCopy();

        Mockito.verify(state, Mockito.times(1)).hasSelectedId();
        Mockito.verifyNoInteractions(processor, parentAdapter);

        // adapter call for parent adapter if selected shape exist
        Mockito.when(state.hasSelectedId()).thenReturn(true);
        adapter.handleCopy();

        Mockito.verify(state, Mockito.times(2)).hasSelectedId();
        Mockito.verify(parentAdapter, Mockito.times(1)).processEvent(ArgumentMatchers.any());
        verifyNoInteractions(processor);
    }

    @Test
    @DisplayName("Paste operation works correctly")
    void testPasteOperation() {
        // adapter not perform any actions if there is no selected shape
        Mockito.when(state.hasCopy()).thenReturn(false);
        adapter.handlePaste();

        Mockito.verify(state, Mockito.times(1)).hasCopy();
        Mockito.verifyNoInteractions(processor, parentAdapter);

        // adapter call for parent adapter if selected shape exist
        Mockito.when(state.hasCopy()).thenReturn(true);
        adapter.handlePaste();

        Mockito.verify(state, Mockito.times(2)).hasCopy();
        Mockito.verify(parentAdapter, Mockito.times(1)).processEvent(ArgumentMatchers.any());
        verifyNoInteractions(processor);
    }
}
