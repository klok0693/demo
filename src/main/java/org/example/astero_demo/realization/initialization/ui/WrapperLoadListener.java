package org.example.astero_demo.realization.initialization.ui;

import com.google.inject.Inject;
import javafx.fxml.LoadListener;
import javafx.scene.Node;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.async.wrappers.AsynchWrapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class WrapperLoadListener implements LoadListener {
    private final List<AsynchWrapper> wrappers;

    @Inject
    public WrapperLoadListener(final AsynchWrapper... wrappers) {
        this.wrappers = Arrays.asList(wrappers);
    }

    @Override
    public void readImportProcessingInstruction(final String target) {}

    @Override
    public void readLanguageProcessingInstruction(final String language) {}

    @Override
    public void readComment(final String comment) {}

    @Override
    public void beginInstanceDeclarationElement(final Class<?> type) {}

    @Override
    public void beginUnknownTypeElement(final String name) {}

    @Override
    public void beginIncludeElement() {}

    @Override
    public void beginReferenceElement() {}

    @Override
    public void beginCopyElement() {}

    @Override
    public void beginRootElement() {}

    @Override
    public void beginPropertyElement(final String name, final Class<?> sourceType) {}

    @Override
    public void beginUnknownStaticPropertyElement(final String name) {}

    @Override
    public void beginScriptElement() {}

    @Override
    public void beginDefineElement() {}

    @Override
    public void readInternalAttribute(final String name, final String value) {}

    @Override
    public void readPropertyAttribute(final String name, final Class<?> sourceType, final String value) {}

    @Override
    public void readUnknownStaticPropertyAttribute(final String name, final String value) {}

    @Override
    public void readEventHandlerAttribute(final String name, final String value) {}

    @Override
    public void endElement(final Object value) {
        if (value instanceof final Node node) {
            final String id = node.getId();
            wrappers.stream()
                    .filter(wrapper -> wrapper.hasInjectedField(id))
                    .forEach(wrapper -> {
                        try {
                            final Object wrappedElement = wrapper.getWrappedElement();
                            final Field privateField = wrappedElement.getClass().getField(id);
                            privateField.setAccessible(true);
                            privateField.set(wrappedElement, node);
                            privateField.setAccessible(false);
                        } catch (final NoSuchFieldException | IllegalAccessException e) {
                            log.error("Enable to set field", e);
                        }
                    });
        }
    }
}
