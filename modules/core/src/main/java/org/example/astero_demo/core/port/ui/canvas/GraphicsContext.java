package org.example.astero_demo.core.port.ui.canvas;

@FunctionalInterface
public interface GraphicsContext<E> extends Graphics<E> {

    E getGraphicsContext();
}
