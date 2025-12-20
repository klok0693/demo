package org.example.astero_demo.api.graphics.color;

import lombok.experimental.UtilityClass;

import static java.lang.Integer.parseInt;

@UtilityClass
public class Colors {

    public static int convert(final Color color) {
        final int alpha = (int) (color.getAlpha() * 255) << 24;
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

        return Color.rgba(red, green, blue, alpha);
    }

    public static double[] HSBtoRGB(double hue, double saturation, double brightness) {
        // normalize the hue
        double normalizedHue = ((hue % 360) + 360) % 360;
        hue = normalizedHue/360;

        double r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = brightness;
        } else {
            double h = (hue - Math.floor(hue)) * 6.0;
            double f = h - java.lang.Math.floor(h);
            double p = brightness * (1.0 - saturation);
            double q = brightness * (1.0 - saturation * f);
            double t = brightness * (1.0 - (saturation * (1.0 - f)));
            switch ((int) h) {
                case 0:
                    r = brightness;
                    g = t;
                    b = p;
                    break;
                case 1:
                    r = q;
                    g = brightness;
                    b = p;
                    break;
                case 2:
                    r = p;
                    g = brightness;
                    b = t;
                    break;
                case 3:
                    r = p;
                    g = q;
                    b = brightness;
                    break;
                case 4:
                    r = t;
                    g = p;
                    b = brightness;
                    break;
                case 5:
                    r = brightness;
                    g = p;
                    b = q;
                    break;
            }
        }
        double[] f = new double[3];
        f[0] = r;
        f[1] = g;
        f[2] = b;
        return f;
    }

    public static double[] RGBtoHSB(double r, double g, double b) {
        double hue, saturation, brightness;
        double[] hsbvals = new double[3];
        double cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        double cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = cmax;
        if (cmax != 0)
            saturation = (cmax - cmin) / cmax;
        else
            saturation = 0;

        if (saturation == 0) {
            hue = 0;
        } else {
            double redc = (cmax - r) / (cmax - cmin);
            double greenc = (cmax - g) / (cmax - cmin);
            double bluec = (cmax - b) / (cmax - cmin);
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0 + redc - bluec;
            else
                hue = 4.0 + greenc - redc;
            hue = hue / 6.0;
            if (hue < 0)
                hue = hue + 1.0;
        }
        hsbvals[0] = hue * 360;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    public static Color convert(final String color) {
        return convert(parseInt(color));
    }

    public static final Color BLACK      = Color.rgb(0, 0, 0);
    public static final Color WHITE      = Color.rgb(255, 255, 255);
    public static final Color RED        = Color.rgb(255, 0, 0);
    public static final Color GREEN      = Color.rgb(0, 255, 0);
    public static final Color BLUE       = Color.rgb(0, 0, 255);

    // Grays
    public static final Color GRAY       = Color.rgb(128, 128, 128);
    public static final Color DARKGRAY   = Color.rgb(64, 64, 64);
    public static final Color LIGHTGRAY  = Color.rgb(192, 192, 192);

    // Extended basic palette
    public static final Color YELLOW     = Color.rgb(255, 255, 0);
    public static final Color CYAN       = Color.rgb(0, 255, 255);
    public static final Color MAGENTA    = Color.rgb(255, 0, 255);

    // JavaFX-style additions
    public static final Color LIGHTBLUE  = Color.rgb(173, 216, 230);
    public static final Color ORANGE     = Color.rgb(255, 165, 0);
    public static final Color PINK       = Color.rgb(255, 192, 203);

    // UI-friendly neutrals
    public static final Color BROWN      = Color.rgb(165, 42, 42);
    public static final Color PURPLE     = Color.rgb(128, 0, 128);
    public static final Color TRANSPARENT = Color.rgba(0f, 0f, 0f, 0f);
}
