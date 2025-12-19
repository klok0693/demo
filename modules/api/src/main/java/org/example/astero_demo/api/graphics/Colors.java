package org.example.astero_demo.api.graphics;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Colors {

    public static final Color BLACK      = Color.rgb255(0, 0, 0);
    public static final Color WHITE      = Color.rgb255(255, 255, 255);
    public static final Color RED        = Color.rgb255(255, 0, 0);
    public static final Color GREEN      = Color.rgb255(0, 255, 0);
    public static final Color BLUE       = Color.rgb255(0, 0, 255);

    // Grays
    public static final Color GRAY       = Color.rgb255(128, 128, 128);
    public static final Color DARKGRAY   = Color.rgb255(64, 64, 64);
    public static final Color LIGHTGRAY  = Color.rgb255(192, 192, 192);

    // Extended basic palette
    public static final Color YELLOW     = Color.rgb255(255, 255, 0);
    public static final Color CYAN       = Color.rgb255(0, 255, 255);
    public static final Color MAGENTA    = Color.rgb255(255, 0, 255);

    // JavaFX-style additions
    public static final Color LIGHTBLUE  = Color.rgb255(173, 216, 230);
    public static final Color ORANGE     = Color.rgb255(255, 165, 0);
    public static final Color PINK       = Color.rgb255(255, 192, 203);

    // UI-friendly neutrals
    public static final Color BROWN      = Color.rgb255(165, 42, 42);
    public static final Color PURPLE     = Color.rgb255(128, 0, 128);
    public static final Color TRANSPARENT = Color.rgba(0f, 0f, 0f, 0f);
}
