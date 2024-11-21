package org.example.astero_demo.adapter.model.entity;

import org.apache.commons.math3.random.RandomDataGenerator;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Factory for creating different shapes based on the provided parameters.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public enum ShapeFactory {
    INSTANCE;

    private final UniqueIdGenerator idGenerator = new UniqueIdGenerator();

    /**
     * Creates a {@link Shape} based on the provided parameters and type.<p>
     * If id is null, it would be generated
     */
    public Shape createShape(
            @Nullable final Integer id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {

        final int generatedId = id != null ? id : idGenerator.generateId();
        return switch (type) {
            case RECT -> new Rectangle(generatedId, priority, x, y, width, height, color);
            case ELLIPSE -> new Ellipse(generatedId, priority, x, y, width, height, color);
        };
    }

    private static class UniqueIdGenerator {
        private static final int SIZE = 10_000;
        private final Collection<Integer> generatedNumbers = new HashSet<>(SIZE);
        private final RandomDataGenerator idGenerator = new RandomDataGenerator();

        public Integer generateId() {
            if (generatedNumbers.size() >= SIZE) {
                throw new IllegalStateException("All unique numbers have been generated.");
            }

            int number;
            do {
                number = idGenerator.nextInt(0, SIZE);
            } while (!generatedNumbers.add(number));

            return number;
        }
    }
}
