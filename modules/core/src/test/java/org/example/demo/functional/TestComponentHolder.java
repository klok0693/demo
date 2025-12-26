package org.example.demo.functional;

public interface TestComponentHolder {

    <T> T getInstance(Class<T> tClass);
}