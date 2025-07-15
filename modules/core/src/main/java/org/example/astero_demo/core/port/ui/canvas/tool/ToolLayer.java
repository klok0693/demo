package org.example.astero_demo.core.port.ui.canvas.tool;

import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitchableView;
import org.example.astero_demo.core.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.drag.DragShapeTool;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert.InsertTool;

/**
 * Layer to for a tool elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ToolLayer<E extends Object> extends CanvasLayer<E, CanvasTool<E>> implements CanvasClickable, ModeSwitchableView {

    public ToolLayer() {
        super(2);
    }
}
