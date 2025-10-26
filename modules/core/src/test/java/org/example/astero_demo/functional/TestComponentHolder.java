package org.example.astero_demo.functional;

public interface TestComponentHolder {

    <T> T getInstance(Class<T> tClass);
}