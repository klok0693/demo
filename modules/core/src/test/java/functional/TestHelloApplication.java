package functional;

import org.example.astero_demo.HelloApplication;

/**
 * Test instance that provide access to inner objects
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class TestHelloApplication extends HelloApplication {

    public <T> T getInstance(final Class<T> tClass) {
        return injector.getInstance(tClass);
    }
}
