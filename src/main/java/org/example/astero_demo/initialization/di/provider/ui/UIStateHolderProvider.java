package org.example.astero_demo.initialization.di.provider.ui;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.state.UIStateHolder;
import org.example.astero_demo.initialization.di.provider.InstanceProvider;

public class UIStateHolderProvider extends InstanceProvider<UIStateHolder> {
    private final StateHolder shapeHolder;

    @Inject
    public UIStateHolderProvider(final StateHolder shapeHolder) {
        this.shapeHolder = shapeHolder;
    }

    @Override
    protected UIStateHolder createInstance() {
        return new UIStateHolder(shapeHolder);
    }
}
