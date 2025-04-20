package org.example.astero_demo.realization.level;

public abstract class WrapperLink<E> {
    protected final E wrappedElement;

    protected WrapperLink(final E wrappedElement) {
        this.wrappedElement = wrappedElement;
    }
}
