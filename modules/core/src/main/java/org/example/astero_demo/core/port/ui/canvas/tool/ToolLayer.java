package org.example.astero_demo.core.port.ui.canvas.tool;

import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitchableView;
import org.example.astero_demo.core.port.ui.canvas.CanvasLayer;

/**
 * Layer to for a tool elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ToolLayer<E extends Object> extends CanvasLayer<E, CanvasTool<E>> implements ModeSwitchableView {

    public ToolLayer() {
        super(2);
    }
}
