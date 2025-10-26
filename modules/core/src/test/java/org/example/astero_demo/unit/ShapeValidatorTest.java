package org.example.astero_demo.unit;

import org.example.astero_demo.core.model.metadata.ShapeParam;
import org.example.astero_demo.core.logic.ShapeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.astero_demo.core.model.entity.ShapeType.ELLIPSE;
import static org.example.astero_demo.core.model.entity.ShapeType.RECT;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test {@link ShapeValidator}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ShapeValidatorTest {

    @Test
    @DisplayName("All correct values are pass")
    void testCorrectValuesArePassed() {
        final ShapeValidator validator = new ShapeValidator();

        assertTrue(validator.isValid(ShapeParam.ID, "0"));
        assertTrue(validator.isValid(ShapeParam.ID, "33"));

        assertTrue(validator.isValid(ShapeParam.X, "-100.0"));
        assertTrue(validator.isValid(ShapeParam.X, "-0.0"));
        assertTrue(validator.isValid(ShapeParam.X, "0.0"));
        assertTrue(validator.isValid(ShapeParam.X, "33.5"));

        assertTrue(validator.isValid(ShapeParam.Y, "-100.0"));
        assertTrue(validator.isValid(ShapeParam.Y, "-0.0"));
        assertTrue(validator.isValid(ShapeParam.Y, "0.0"));
        assertTrue(validator.isValid(ShapeParam.Y, "33.5"));

        assertTrue(validator.isValid(ShapeParam.WIDTH, "-0.0"));
        assertTrue(validator.isValid(ShapeParam.WIDTH, "0.0"));
        assertTrue(validator.isValid(ShapeParam.WIDTH, "33.5"));

        assertTrue(validator.isValid(ShapeParam.HEIGHT, "-0.0"));
        assertTrue(validator.isValid(ShapeParam.HEIGHT, "0.0"));
        assertTrue(validator.isValid(ShapeParam.HEIGHT, "33.5"));

        assertTrue(validator.isValid(ShapeParam.PRIORITY, "-0.0"));
        assertTrue(validator.isValid(ShapeParam.PRIORITY, "0.0"));
        assertTrue(validator.isValid(ShapeParam.PRIORITY, "33.5"));

        assertTrue(validator.isValid(ShapeParam.COLOR, "-100"));
        assertTrue(validator.isValid(ShapeParam.COLOR, "-0"));
        assertTrue(validator.isValid(ShapeParam.COLOR, "0"));
        assertTrue(validator.isValid(ShapeParam.COLOR, "33"));

        assertTrue(validator.isValid(ShapeParam.TYPE, ELLIPSE.name()));
        assertTrue(validator.isValid(ShapeParam.TYPE, RECT.name()));
    }

    @Test
    @DisplayName("All incorrect values are not pass")
    void testIncorrectValuesAreNotPassed() {
        final ShapeValidator validator = new ShapeValidator();

        assertFalse(validator.isValid(ShapeParam.ID, "-33"));
        assertFalse(validator.isValid(ShapeParam.ID, "-0.0"));
        assertFalse(validator.isValid(ShapeParam.ID, null));

        assertFalse(validator.isValid(ShapeParam.X, ""));
        assertFalse(validator.isValid(ShapeParam.X, "   "));
        assertFalse(validator.isValid(ShapeParam.X, "-0.0www"));
        assertFalse(validator.isValid(ShapeParam.X, "www"));
        assertFalse(validator.isValid(ShapeParam.X, null));

        assertFalse(validator.isValid(ShapeParam.Y, ""));
        assertFalse(validator.isValid(ShapeParam.Y, "   "));
        assertFalse(validator.isValid(ShapeParam.Y, "-0.0www"));
        assertFalse(validator.isValid(ShapeParam.Y, "www"));
        assertFalse(validator.isValid(ShapeParam.Y, null));

        assertFalse(validator.isValid(ShapeParam.WIDTH, "-100.0"));
        assertFalse(validator.isValid(ShapeParam.WIDTH, null));
        assertFalse(validator.isValid(ShapeParam.WIDTH, "null"));
        assertFalse(validator.isValid(ShapeParam.WIDTH, ""));
        assertFalse(validator.isValid(ShapeParam.WIDTH, "  "));
        assertFalse(validator.isValid(ShapeParam.WIDTH, "0w0"));

        assertFalse(validator.isValid(ShapeParam.HEIGHT, "-100.0"));
        assertFalse(validator.isValid(ShapeParam.HEIGHT, null));
        assertFalse(validator.isValid(ShapeParam.HEIGHT, "null"));
        assertFalse(validator.isValid(ShapeParam.HEIGHT, ""));
        assertFalse(validator.isValid(ShapeParam.HEIGHT, "  "));
        assertFalse(validator.isValid(ShapeParam.HEIGHT, "0w0"));

        assertFalse(validator.isValid(ShapeParam.PRIORITY, "-100.0"));
        assertFalse(validator.isValid(ShapeParam.PRIORITY, null));
        assertFalse(validator.isValid(ShapeParam.PRIORITY, "null"));
        assertFalse(validator.isValid(ShapeParam.PRIORITY, ""));
        assertFalse(validator.isValid(ShapeParam.PRIORITY, "  "));
        assertFalse(validator.isValid(ShapeParam.PRIORITY, "0w0"));

        assertFalse(validator.isValid(ShapeParam.COLOR, ""));
        assertFalse(validator.isValid(ShapeParam.COLOR, null));

        assertFalse(validator.isValid(ShapeParam.TYPE, "-100.0"));
        assertFalse(validator.isValid(ShapeParam.TYPE, "100.0"));
        assertFalse(validator.isValid(ShapeParam.TYPE, "0"));
        assertFalse(validator.isValid(ShapeParam.TYPE, ""));
        assertFalse(validator.isValid(ShapeParam.TYPE, "   "));
        assertFalse(validator.isValid(ShapeParam.TYPE, "0.0www"));
        assertFalse(validator.isValid(ShapeParam.TYPE, "www"));
        assertFalse(validator.isValid(ShapeParam.TYPE, null));
    }

    @Test
    @DisplayName("Corrupted values throw exception")
    void testCorruptedValuesCausedException() {
        final ShapeValidator validator = new ShapeValidator();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> validator.isValid(null, "any"));
    }
}
