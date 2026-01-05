package org.example.demo;

import com.google.inject.Injector;
import com.google.inject.Module;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.core.port.ui.RootView;
import org.example.demo.core.port.ui.ToolBarPanelView;
import org.example.demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.demo.qt.initialization.di.QtModule;
import org.example.demo.qt.port.ui.QtMemoryView;
import org.example.demo.qt.port.ui.element.QtCanvasUI;
import org.example.demo.qt.port.ui.graphics.QtPainterFactory;
import org.example.demo.realization.initialization.launch.AppInitializer;
import org.example.demo.realization.level.async.NonBlockingForegroundExecutor;

import java.util.List;
import java.util.stream.Stream;

/**
 * application initializer
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */

@Slf4j
public class QtAppInitializer extends AppInitializer {

    @Override
    protected List<Module> getModules() {
        final var modules = super.getModules();
        modules.add(new QtModule());
        return modules;
    }

    @Override
    protected Object launchGUI(final Injector injector) {
        Stream.of(
                        ToolBarPanelView.class,
                        RootView.class,
                        QtCanvasUI.class,
                        QtPainterFactory.class,
                        ShapeCanvasView.class,
                        NonBlockingForegroundExecutor.class
                )
                .map(injector::getInstance)
                .map(QtMemoryView.class::cast)
                .forEach(qtMemoryView -> {
                    try {
                        qtMemoryView.initialize();
                    }
                    catch (final Throwable e) {
                        log.error("Can't set up memory segment: ", e);
                    }
                });
        return null;
    }
}
