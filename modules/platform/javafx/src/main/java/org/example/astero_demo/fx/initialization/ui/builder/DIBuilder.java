package org.example.astero_demo.fx.initialization.ui.builder;

import com.google.inject.Injector;
import javafx.util.Builder;

/**
 * Service-locator for JavaFX nodes, injected via guice.
 * Necessity of such classes after investigation looks
 * blurry. Class stay as it might be helpful in future
 */
@Deprecated
public class DIBuilder<T> implements Builder<T> {
    private final Injector injector;
    private final Class<T> type;

    public DIBuilder(final Injector injector, final Class<T> type) {
        this.injector = injector;
        this.type = type;
    }

    @Override
    public T build() {
        return injector.getInstance(type);
    }
}
