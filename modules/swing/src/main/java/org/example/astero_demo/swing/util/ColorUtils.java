package org.example.astero_demo.swing.util;

import lombok.experimental.UtilityClass;
import org.example.astero_demo.api.graphics.color.Colors;

import java.awt.*;

import static java.lang.Integer.parseInt;

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
                org.example.astero_demo.api.graphics.color.Color.rgba(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        color.getAlpha()
                )
        );
    }

    public static Color convert(final int color) {
        org.example.astero_demo.api.graphics.color.Color apiColor = Colors.convert(color);
        return new Color(
                (int) apiColor.getRed(),
                (int) apiColor.getGreen(),
                (int) apiColor.getBlue(),
                (int) apiColor.getAlpha()/* / 255.0*/
        );
    }

    public static Color convert(final String color) {
        return convert(parseInt(color));
    }

/*    public static int convert(final Color color) {
        final int alpha = (int) (color.getAlpha() *//*.getOpacity()*//* * 255) << 24;
        final int red = (int) (color.getRed() * 255) << 16;
        final int green = (int) (color.getGreen() * 255) << 8;
        final int blue = (int) (color.getBlue() * 255);
        return alpha | red | green | blue;
    }

    public static Color convert(final int color) {
        final int alpha = (color >> 24) & 0xFF;
        final int red = (color >> 16) & 0xFF;
        final int green = (color >> 8) & 0xFF;
        final int blue = color & 0xFF;

        *//*return Color.rgb(red, green, blue, alpha / 255.0);*//*
        return Color.green;
    }

    public static Color convert(final String color) {
        return convert(parseInt(color));
    }*/
}
