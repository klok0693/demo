package org.example.astero_demo.fx.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.core.port.os.OSClipboard;
import org.example.astero_demo.fx.port.os.FxClipboard;

public class FxModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new InitializationModule());
        install(new FxAsynchModule());
        install(new UIElementModule());
        install(new UIViewModule());
    }
}
