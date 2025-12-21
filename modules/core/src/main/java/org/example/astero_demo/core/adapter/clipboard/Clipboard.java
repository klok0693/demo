package org.example.astero_demo.core.adapter.clipboard;

public interface Clipboard<T, S> {

    void put(T obj);

    S get();

    boolean hasCopy();

    void clear();
}
