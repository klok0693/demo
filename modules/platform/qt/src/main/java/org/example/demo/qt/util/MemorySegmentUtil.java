package org.example.demo.qt.util;

import lombok.experimental.UtilityClass;
import org.example.demo.core.port.ui.ToolBarPanelView;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.Collection;
import java.util.stream.Stream;

import static java.lang.foreign.ValueLayout.*;

/**
 * @author Pilip Yurchanka
 * @since v1.2
 */
@UtilityClass
public class MemorySegmentUtil {

    /**
     * Transform {@link Stream} of strings to
     * ABI-compatible <i>const char* const*</i> collection
     */
    public static MemorySegment transform(
            final Stream<String> stream,
            final Arena arena) {

        final MemorySegment[] cStrings = stream
                .map(arena::allocateUtf8String)
                .toArray(MemorySegment[]::new);

        return allocateArray(cStrings, arena);
    }

    private static MemorySegment allocateArray(
            final MemorySegment[] segments,
            final Arena arena) {

        final MemorySegment ptrArray = arena.allocateArray(ADDRESS, segments.length);

        for (int i = 0; i < segments.length; i++) {
            ptrArray.setAtIndex(ADDRESS, i, segments[i]);
        }

        return ptrArray;
    }
}
