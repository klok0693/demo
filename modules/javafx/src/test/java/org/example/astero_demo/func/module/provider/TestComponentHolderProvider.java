package org.example.astero_demo.func.module.provider;

import com.google.inject.Provider;
import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.func.TestHelloApplication;

public class TestComponentHolderProvider implements Provider<TestHelloApplication> {
    private static TestHelloApplication holder;

    @Override
    public TestHelloApplication get() {
        if (holder == null) {
            holder = new TestHelloApplication();
        }
        return holder;
    }
}
