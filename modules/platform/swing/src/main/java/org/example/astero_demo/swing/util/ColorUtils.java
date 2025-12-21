package org.example.astero_demo.swing.util;

import lombok.experimental.UtilityClass;
import org.example.astero_demo.api.graphics.color.Colors;

import java.awt.*;

import static java.lang.Integer.parseInt;

/**
 * Convert from/to RGB color
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
@UtilityClass
public class ColorUtils {

    public static int convert(final Color color) {
        return Colors.convert(
                org.example.astero_demo.api.graphics.color.Color.rgba(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        color.getAlpha()
                )
        );
    }

    public static Color convert(final int color) {
        final org.example.astero_demo.api.graphics.color.Color apiColor = Colors.convert(color);
        return new Color(
                (int) apiColor.getRed(),
                (int) apiColor.getGreen(),
                (int) apiColor.getBlue(),
                (int) apiColor.getAlpha()
        );
    }
}
