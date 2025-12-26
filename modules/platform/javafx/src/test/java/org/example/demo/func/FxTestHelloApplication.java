package org.example.demo.func;

import org.example.demo.FxHelloApplication;
import org.example.demo.functional.TestComponentHolder;

/**
 * Test instance that provide access to inner objects
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class FxTestHelloApplication extends FxHelloApplication implements TestComponentHolder {

    @Override
    public <T> T getInstance(final Class<T> tClass) {
        return getInjector().getInstance(tClass);
    }
}
