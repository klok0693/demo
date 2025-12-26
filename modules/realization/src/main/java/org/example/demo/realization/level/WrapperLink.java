package org.example.demo.realization.level;

/**
 * To separate all modification in code behavior<p>
 * from business logic, wrappers are widely used
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class WrapperLink<E> {
    protected final E wrappedElement;

    protected WrapperLink(final E wrappedElement) {
        this.wrappedElement = wrappedElement;
    }
}
