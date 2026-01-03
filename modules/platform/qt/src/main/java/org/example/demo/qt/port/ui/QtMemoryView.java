package org.example.demo.qt.port.ui;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

import static java.lang.invoke.MethodType.methodType;

public interface QtMemoryView {
    Linker LINKER = Linker.nativeLinker();
    SymbolLookup LOOKUP = createLookup();

    private static SymbolLookup createLookup() {
        try {
            return SymbolLookup.libraryLookup("libui.so", Arena.global());
        }
        catch (final Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    default MemorySegment bindMethodToNative(
            final String javaName,
            final Class<?> rtype,
            final Class<?>[] ptypes,
            final FunctionDescriptor upcallDescription,
            final String nativeRefName,
            final String nativeName
    ) throws Throwable {
        final MemorySegment segment =
                createMemorySegment(javaName, rtype, ptypes, upcallDescription);

        final MemorySegment qtToolBarRef =
                (MemorySegment) LINKER.downcallHandle(
                        LOOKUP.find(nativeRefName).orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.ADDRESS)
                ).invoke();

        final MethodHandle downcallHandle = findNative(
                nativeName,
                FunctionDescriptor.ofVoid(
                        ValueLayout.ADDRESS,
                        ValueLayout.ADDRESS,
                        ValueLayout.ADDRESS));

        downcallHandle.invoke(qtToolBarRef, segment, MemorySegment.NULL);

        return segment;
    }

    default MemorySegment createMemorySegment(
            final String javaName,
            final Class<?> rtype,
            final Class<?>[] ptypes,
            final FunctionDescriptor upcallDescription)
            throws Throwable {

        try {
            final MethodHandle boundHandle = MethodHandles.lookup()
                    .findVirtual(this.getClass(), javaName, methodType(rtype, ptypes))
                    .bindTo(this);

            final MemorySegment callbackSegment = LINKER.upcallStub(
                    boundHandle,
                    upcallDescription,
                    Arena.global());

            return callbackSegment;
        }
        catch (final Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    default MethodHandle findNative(
            final String nativeName,
            final FunctionDescriptor downcallDescription) {

        return LINKER.downcallHandle(
                LOOKUP.find(nativeName).orElseThrow(),
                downcallDescription
        );
    }

    void initialize() throws Throwable;
}
