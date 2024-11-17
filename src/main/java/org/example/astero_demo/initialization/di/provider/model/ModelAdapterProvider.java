package org.example.astero_demo.initialization.di.provider.model;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeFactory;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.initialization.di.provider.InstanceProvider;

public class ModelAdapterProvider extends InstanceProvider<ModelAdapter> {
    private final ShapeFactory factory;
    private final StateHolder holder;

    @Inject
    public ModelAdapterProvider(final ShapeFactory factory, final StateHolder holder) {
        this.factory = factory;
        this.holder = holder;
    }

    @Override
    protected ModelAdapter createInstance() {
        return new ModelAdapter(factory, holder);
    }
}
