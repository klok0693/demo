package org.example.astero_demo.realization.initialization.launch;

/**
 * @since 1.2
 * @author Pilip Yurchanka
 */
public interface Initializer<E> {

    E initialize(String... args);
}
