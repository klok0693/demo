package org.example.astero_demo.swing.realization.level.async;

import com.google.inject.Inject;
import org.example.astero_demo.SwingHelloApplication;
import org.example.astero_demo.realization.initialization.launch.Application;
import org.example.astero_demo.HelloApplication;
import org.example.astero_demo.realization.level.async.AsynchWrapper;
import org.example.astero_demo.realization.level.async.BlockingForegroundExecutor;

import javax.swing.*;

/**
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class SwingHelloApplicationAsyncWrapper
        extends AsynchWrapper<SwingHelloApplication, BlockingForegroundExecutor>
        implements HelloApplication<JFrame> {

    @Inject
    public SwingHelloApplicationAsyncWrapper(
            @Application final SwingHelloApplication wrappedElement,
            final BlockingForegroundExecutor executor) {
        super(wrappedElement, executor);
    }

    @Override
    public JFrame launchGUI() {
        return executor.execute(wrappedElement::launchGUI);
    }
}
