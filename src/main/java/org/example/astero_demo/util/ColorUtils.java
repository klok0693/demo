package org.example.astero_demo.util;

import javafx.scene.paint.Color;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColorUtils {

    public static int convert(final Color color) {
        final int alpha = (int) (color.getOpacity() * 255) << 24;
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

        return Color.rgb(red, green, blue, alpha / 255.0);
    }
}
