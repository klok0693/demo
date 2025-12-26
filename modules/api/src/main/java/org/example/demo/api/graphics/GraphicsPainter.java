package org.example.demo.api.graphics;

import org.example.demo.api.graphics.color.Color;

/**
 * Custom drawing logic, existed in some UI elements like<p>
 * canvas tools, required a platform-independent graphics API.<p>
 * This class provide it
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public interface GraphicsPainter {

    void setFill(Color color);

    void strokeRect(double x, double y, double width, double height);

    void fillRect(double x, double y, double width, double height);

    void fillOval(double x, double y, double width, double height);

    void setOpacity(double opacity);

    void setStroke(Color color);

    void setLineWidth(double width);
}
