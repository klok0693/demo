package org.example.astero_demo.api.graphics.color;

import lombok.Getter;

@Getter
public class Color {
    private static final String WRONG_RANGE_MESSAGE = "Color.rgb's %s parameter (%d) expects color values 0-255";
    private static final double RANGE_FACTOR = 255.0;
    private static final double DARKER_BRIGHTER_FACTOR = 0.7d;
    private static final double BRIGHTER_FACTOR = 1.0d / DARKER_BRIGHTER_FACTOR;

    private final double red; // 0..1
    private final double green;
    private final double blue;
    private final double alpha;

    private Color(final double r, final double g, final double b, final double a) {
        this.red = r;// clamp(r);
        this.green = g;// clamp(g);
        this.blue = b;// clamp(b);
        this.alpha = a;// clamp(a);
    }

    /* ---------- Factory methods ---------- */

    public static Color rgb(final int r, final int g, final int b) {
        return rgba(r, g, b, (int) RANGE_FACTOR);
    }

    public static Color rgba(final int r, final int g, final int b, final int a) {
        return rgba(r / RANGE_FACTOR, g / RANGE_FACTOR, b / RANGE_FACTOR, a / RANGE_FACTOR);
    }

    public static Color rgb(final double r, final double g, final double b) {
        return rgba(r, g, b, 1.0);
    }

    public static Color rgba(final double r, final double g, final double b, final double a) {
        checkRGBA(r, g, b, a);
        return new Color(r, g, b, a);
    }

    private static void checkRGBA(final double red, final double green, final double blue, final double alpha) {
        if (red < 0.0 || red > 1.0) {
            throw generateException("red", red);
        }
        if (green < 0.0 || green > 1.0) {
            throw generateException("green", green);
        }
        if (blue < 0.0 || blue > 1.0) {
            throw generateException("blue", blue);
        }
        if (alpha < 0.0 || alpha > 1.0) {
            throw generateException("alpha", alpha);
        }
    }

    private static IllegalArgumentException generateException(final String type, final double value) {
        return new IllegalArgumentException(String.format(WRONG_RANGE_MESSAGE, type, (int) (value * RANGE_FACTOR)));
    }

    /* ---------- Brightness manipulation ---------- */

    public Color darker() {
        return deriveColor(0, 1.0, DARKER_BRIGHTER_FACTOR, 1.0);
    }

    public Color brighter() {
        return deriveColor(0, 1.0, 1.0 / DARKER_BRIGHTER_FACTOR, 1.0);
    }

    public Color deriveColor(
            final double hueShift,
            final double saturationFactor,
            final double brightnessFactor,
            final double opacityFactor) {

        final double[] hsb = Colors.RGBtoHSB(red, green, blue);

        /* Allow brightness increase of black color */
        double b = hsb[2];
        if (b == 0 && brightnessFactor > 1.0) {
            b = 0.05;
        }

        /* the tail "+ 360) % 360" solves shifts into negative numbers */
        final double h = (((hsb[0] + hueShift) % 360) + 360) % 360;
        final double s = Math.max(Math.min(hsb[1] * saturationFactor, 1.0), 0.0);
        b = Math.max(Math.min(b * brightnessFactor, 1.0), 0.0);
        final double a = Math.max(Math.min(alpha * opacityFactor, 1.0), 0.0);
        return hsb(h, s, b, a);
    }

    public static Color hsb(
            final double hue,
            final double saturation,
            final double brightness,
            final double opacity) {
        final double[] rgb = Colors.HSBtoRGB(hue, saturation, brightness);
        return new Color(rgb[0], rgb[1], rgb[2], opacity);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Color) {
            final Color other = (Color) obj;
            return red == other.red
                    && green == other.green
                    && blue == other.blue
                    && alpha == other.alpha;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int r = (int)Math.round(red * RANGE_FACTOR);
        final int g = (int)Math.round(green * RANGE_FACTOR);
        final int b = (int)Math.round(blue * RANGE_FACTOR);
        final int a = (int)Math.round(alpha * RANGE_FACTOR);
        return to32BitInteger(r, g, b, a);
    }

    private static int to32BitInteger(
            final int red,
            final int green,
            final int blue,
            final int alpha) {
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
        final int r = (int)Math.round(red * RANGE_FACTOR);
        final int g = (int)Math.round(green * RANGE_FACTOR);
        final int b = (int)Math.round(blue * RANGE_FACTOR);
        final int o = (int)Math.round(alpha * RANGE_FACTOR);
        return String.format("0x%02x%02x%02x%02x" , r, g, b, o);
    }

    private static double clamp(final double v) {
        return Math.max(0f, Math.min(1f, v));
    }
}
