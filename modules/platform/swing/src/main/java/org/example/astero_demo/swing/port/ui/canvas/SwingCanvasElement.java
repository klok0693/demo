package org.example.astero_demo.swing.port.ui.canvas;

import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.api.graphics.Drawable;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

/**
 * Swing realization of {@link CanvasElement}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
@FunctionalInterface
public interface SwingCanvasElement extends Drawable<SwingPainter> {
}
