package org.example.demo.swing.util;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @since 1.2
 * @author Pilip Yurchanka
 */
@UtilityClass
public class SwingConstants {
    private static final int BORDER_WIDTH = 10;
    private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(
            BORDER_WIDTH,
            BORDER_WIDTH,
            BORDER_WIDTH,
            BORDER_WIDTH);
    
    public static final Border VIEW_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                    EMPTY_BORDER,
                    BorderFactory.createLineBorder(Color.BLACK)),
            EMPTY_BORDER
    );
}
