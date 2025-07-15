package org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.CanvasClickable;
import org.example.astero_demo.core.port.ui.canvas.tool.SelectionFrame;

import java.util.Arrays;
import java.util.List;

import static org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment.*;
import static org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment.CENTER_LEFT;

public interface ModificableSelectionFrame<E extends Object> extends SelectionFrame<E>, CanvasClickable {

}
