package org.example.astero_demo.api.graphics.color;

import lombok.Getter;

@Getter
public class Color {
    private static final float DARKER_BRIGHTER_FACTOR = 0.7f;
    private static final float BRIGHTER_FACTOR = 1.0f / DARKER_BRIGHTER_FACTOR;

    private final double red; // 0..1
    private final double green;
    private final double blue;
    private final double alpha;

    private Color(double r, double g, double b, double a) {
        this.red = clamp(r);
        this.green = clamp(g);
        this.blue = clamp(b);
        this.alpha = clamp(a);
    }

    /* ---------- Factory methods ---------- */

    public static Color rgb(double r, double g, double b) {
        return new Color(r, g, b, 1.0f);
    }

    public static Color rgba(double r, double g, double b, double a) {
        return new Color(r, g, b, a);
    }

    public static Color rgb255(int r, int g, int b) {
        return rgb(r / 255f, g / 255f, b / 255f);
    }

    public static Color rgba255(int r, int g, int b, int a) {
        return rgba(r / 255f, g / 255f, b / 255f, a / 255f);
    }

    /* ---------- Brightness manipulation ---------- */

    public Color darker() {
        return new Color(
                red * DARKER_BRIGHTER_FACTOR,
                green * DARKER_BRIGHTER_FACTOR,
                blue * DARKER_BRIGHTER_FACTOR,
                alpha
        );
    }

    public Color lighter() {
        return new Color(
                Math.min(red * BRIGHTER_FACTOR, 1.0f),
                Math.min(green * BRIGHTER_FACTOR, 1.0f),
                Math.min(blue * BRIGHTER_FACTOR, 1.0f),
                alpha
        );
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj instanceof Color) {
            Color other = (Color) obj;
            return red == other.red
                    && green == other.green
                    && blue == other.blue
                    && alpha == other.alpha;
        } else return false;
    }

    @Override
    public int hashCode() {
        int r = (int)Math.round(red * 255.0);
        int g = (int)Math.round(green * 255.0);
        int b = (int)Math.round(blue * 255.0);
        int a = (int)Math.round(alpha * 255.0);
        return to32BitInteger(r, g, b, a);
    }

    private static int to32BitInteger(int red, int green, int blue, int alpha) {
        int i = red;
        i = i << 8;
        i = i | green;
        i = i << 8;
        i = i | blue;
        i = i << 8;
        i = i | alpha;
        return i;
    }

    @Override
    public String toString() {
        int r = (int)Math.round(red * 255.0);
        int g = (int)Math.round(green * 255.0);
        int b = (int)Math.round(blue * 255.0);
        int o = (int)Math.round(alpha * 255.0);
        return String.format("0x%02x%02x%02x%02x" , r, g, b, o);
    }

    private static double clamp(double v) {
        return Math.max(0f, Math.min(1f, v));
    }
}
