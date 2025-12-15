package org.example.astero_demo.func;

import org.example.astero_demo.HelloApplication;
import org.example.astero_demo.functional.TestComponentHolder;

/**
 * Test instance that provide access to inner objects
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class TestHelloApplication extends HelloApplication implements TestComponentHolder {

    @Override
    public <T> T getInstance(final Class<T> tClass) {
        return injector.getInstance(tClass);
    }
}
