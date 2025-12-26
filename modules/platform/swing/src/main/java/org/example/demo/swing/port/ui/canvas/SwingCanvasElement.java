package org.example.demo.swing.port.ui.canvas;

import org.example.demo.core.port.ui.canvas.CanvasElement;
import org.example.demo.api.graphics.Drawable;
import org.example.demo.swing.port.ui.graphics.SwingPainter;

/**
 * Swing realization of {@link CanvasElement}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
@FunctionalInterface
public interface SwingCanvasElement extends Drawable<SwingPainter> {
}
