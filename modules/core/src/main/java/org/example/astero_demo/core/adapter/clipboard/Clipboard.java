package org.example.astero_demo.core.adapter.clipboard;

import org.example.astero_demo.model.metadata.dto.ShapeParams;

public interface Clipboard<T, S> {

    void put(T obj);

    S get();

    boolean hasCopy();

    void clear();
}
