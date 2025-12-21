package org.example.astero_demo.swing.util;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

@UtilityClass
public class SwingConstants {
    private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    public static final Border VIEW_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                    EMPTY_BORDER,
                    BorderFactory.createLineBorder(Color.BLACK)),
            EMPTY_BORDER
    );
}
