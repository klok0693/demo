package org.example.demo.qt.port.keyboard;

import org.example.demo.api.keyboard.Key;
import org.example.demo.core.adapter.keyboard.OperationAdapter;
import org.example.demo.core.port.keyboard.RootShortcutHandler;
import org.example.demo.qt.port.ui.QtMemoryView;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.util.Locale;

import static java.lang.foreign.ValueLayout.*;

/**
 * Bind keyboard shortcuts with specific operations and delegate it to the {@link OperationAdapter}.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class QtRootShortcutHandler extends RootShortcutHandler implements QtMemoryView {
    private static final String NATIVE_REF_NAME = "ui_keyboard_view_get";
    private static final String NATIVE_SET_KEY_INPUT_CALLBACK_NAME = "set_key_callback";

    private MemorySegment processKeySegment;

    public QtRootShortcutHandler(final OperationAdapter keyBoardAdapter) {
        super(keyBoardAdapter);
    }

    @Override
    public void initialize() throws Throwable {
        this.processKeySegment = bindMethodToNative(
                "processKeyInput",
                void.class,
                new Class[]{ MemorySegment.class, boolean.class, boolean.class },
                FunctionDescriptor.ofVoid(ADDRESS, JAVA_BOOLEAN, JAVA_BOOLEAN),
                NATIVE_REF_NAME,
                NATIVE_SET_KEY_INPUT_CALLBACK_NAME
        );
    }

    public void processKeyInput(
            final MemorySegment keySegment,
            final boolean isCtrl,
            final boolean isShift) {
        final MemorySegment sizedSegment = keySegment.reinterpret(Long.MAX_VALUE);
        final String keyStr = sizedSegment.getUtf8String(0);

        System.out.println("java receive " + keyStr + " " + isCtrl);

        process(
                switch (keyStr.toLowerCase(Locale.ROOT)) {
                    case "z" -> Key.Z;
                    case "x" -> Key.X;
                    case "c" -> Key.C;
                    case "v" -> Key.V;
                    case "del" -> Key.DELETE;
                    default -> null;
                }
                , isCtrl
        );
    }

/*    @Override
    public void handle(final KeyEvent keyEvent) {
        process(
                switch (keyEvent.getCode()) {
                    case Z -> Key.Z;
                    case X -> Key.X;
                    case C -> Key.C;
                    case V -> Key.V;
                    case DELETE -> Key.DELETE;
                    default -> null;
        }
        , keyEvent.isControlDown());
    }*/
}
