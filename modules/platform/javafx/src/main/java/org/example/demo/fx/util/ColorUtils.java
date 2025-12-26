package org.example.demo.fx.util;

import javafx.scene.paint.Color;
import lombok.experimental.UtilityClass;
import org.example.demo.api.graphics.color.Colors;

/**
 * Convert from/to sRGB color
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@UtilityClass
public class ColorUtils {

    public static int convert(final Color color) {
        return Colors.convert(
                org.example.demo.api.graphics.color.Color.rgba(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        color.getOpacity()
                )
        );
    }

    public static Color convert(final int color) {
        final org.example.demo.api.graphics.color.Color apiColor = Colors.convert(color);
        return Color.color(
                apiColor.getRed(),
                apiColor.getGreen(),
                apiColor.getBlue(),
                apiColor.getAlpha()
        );
    }
}
