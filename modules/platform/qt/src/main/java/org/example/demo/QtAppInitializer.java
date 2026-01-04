package org.example.demo;

import com.google.inject.Injector;
import com.google.inject.Module;
import org.example.demo.core.port.ui.RootView;
import org.example.demo.core.port.ui.ToolBarPanelView;
import org.example.demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.demo.qt.initialization.di.QtModule;
import org.example.demo.qt.port.ui.QtMemoryView;
import org.example.demo.qt.port.ui.QtRootView;
import org.example.demo.qt.port.ui.QtToolBarView;
import org.example.demo.qt.port.ui.element.QtCanvasUI;
import org.example.demo.qt.port.ui.graphics.QtPainterFactory;
import org.example.demo.realization.initialization.launch.AppInitializer;
import org.example.demo.realization.level.async.NonBlockingForegroundExecutor;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;

/**
 * application initializer
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */

public class QtAppInitializer extends AppInitializer {
    private static MemorySegment CALLBACK_STUB;
    private static Linker LINKER/* = Linker.nativeLinker()*/;
    private static SymbolLookup LOOKUP /*= SymbolLookup.libraryLookup(*//*"ui"*//*"libui.so", Arena.global())*/;

    public QtAppInitializer() {
        LINKER = Linker.nativeLinker();
        LOOKUP = SymbolLookup.libraryLookup(/*"ui"*/"libui.so", Arena.global());
    }

    @Override
    protected List<Module> getModules() {
        final var modules = super.getModules();
        modules.add(new QtModule());

        System.out.println("Qt module loaded");

        return modules;
    }

    @Override
    protected Object launchGUI(final Injector injector) {
        try {
            ((QtMemoryView) injector.getInstance(ToolBarPanelView.class)).initialize();
            ((QtMemoryView) injector.getInstance(RootView.class)).initialize();
            ((QtMemoryView) injector.getInstance(QtCanvasUI.class)).initialize();
            ((QtMemoryView) injector.getInstance(QtPainterFactory.class)).initialize();
            ((QtMemoryView) injector.getInstance(ShapeCanvasView.class)).initialize();
            ((QtMemoryView) injector.getInstance(NonBlockingForegroundExecutor.class)).initialize();

            setupCallback();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        //FxHelloApplication.setInjector(injector);
        //Application.launch(FxHelloApplication.class);
        return null;
    }

    public void handleStatus(final int status) {
        System.out.println("!!!!!!!!!!!!! " + status);
    }

    public void setupCallback() throws Throwable {
        final MethodHandle handle = MethodHandles.lookup().findVirtual(
                QtAppInitializer.class, "handleStatus",
                MethodType.methodType(void.class, int.class));

        final MethodHandle boundHandle = handle.bindTo(this);

        System.out.println("Handle ready");

        CALLBACK_STUB = LINKER.upcallStub(
                boundHandle,
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT),
                Arena.global()
        );

        System.out.println("Stub ready");

        final MethodHandle setCallback = LINKER.downcallHandle(
                LOOKUP.find("setStatusCallback").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
        );
        setCallback.invoke(CALLBACK_STUB);

        System.out.println("Call c++ func");

        final MethodHandle setToolState = LINKER.downcallHandle(
                LOOKUP.find("setToolState").orElseThrow(),
                FunctionDescriptor.ofVoid(
                        ValueLayout.JAVA_INT,
                        ValueLayout.JAVA_INT
                )
        );

        setToolState.invoke(3, 1);

        System.out.println("FFM init finished");
    }
}
