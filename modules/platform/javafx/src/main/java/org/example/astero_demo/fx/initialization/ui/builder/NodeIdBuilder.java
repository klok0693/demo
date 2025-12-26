package org.example.astero_demo.fx.initialization.ui.builder;

import javafx.scene.Node;
import javafx.util.Builder;
import org.example.astero_demo.core.port.ui.markup.ElementID;

import java.util.HashMap;

public abstract class NodeIdBuilder<E extends Node> extends HashMap<String, Object> implements Builder<E> {
    private static final String ID = "id";

    @Override
    public E build() {
        final E node = buildNode();
        setId(node);
        setUpNode(node);

        return node;
    }

    protected void setId(final E node) {
        if (get(ID) instanceof final ElementID elem) {
            node.setId(elem.toString());
        }
    }

    protected String getString(final String key) {
        return (String) get(key);
    }

    protected double getDouble(final String key) {
        return Double.parseDouble(getString(key));
    }

    protected boolean getBoolean(final String key) {
        return Boolean.parseBoolean(key);
    }

    protected abstract E buildNode();

    protected abstract void setUpNode(E node);
}
