// Generated by jextract

package kraylib.ffm;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct NPatchInfo {
 *     Rectangle source;
 *     int left;
 *     int top;
 *     int right;
 *     int bottom;
 *     int layout;
 * }
 * }
 */
public class NPatchInfo {

    NPatchInfo() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        Rectangle.layout().withName("source"),
        Raylib.C_INT.withName("left"),
        Raylib.C_INT.withName("top"),
        Raylib.C_INT.withName("right"),
        Raylib.C_INT.withName("bottom"),
        Raylib.C_INT.withName("layout")
    ).withName("NPatchInfo");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final GroupLayout source$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("source"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Rectangle source
     * }
     */
    public static final GroupLayout source$layout() {
        return source$LAYOUT;
    }

    private static final long source$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Rectangle source
     * }
     */
    public static final long source$offset() {
        return source$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Rectangle source
     * }
     */
    public static MemorySegment source(MemorySegment struct) {
        return struct.asSlice(source$OFFSET, source$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Rectangle source
     * }
     */
    public static void source(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, source$OFFSET, source$LAYOUT.byteSize());
    }

    private static final OfInt left$LAYOUT = (OfInt)$LAYOUT.select(groupElement("left"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int left
     * }
     */
    public static final OfInt left$layout() {
        return left$LAYOUT;
    }

    private static final long left$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int left
     * }
     */
    public static final long left$offset() {
        return left$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int left
     * }
     */
    public static int left(MemorySegment struct) {
        return struct.get(left$LAYOUT, left$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int left
     * }
     */
    public static void left(MemorySegment struct, int fieldValue) {
        struct.set(left$LAYOUT, left$OFFSET, fieldValue);
    }

    private static final OfInt top$LAYOUT = (OfInt)$LAYOUT.select(groupElement("top"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int top
     * }
     */
    public static final OfInt top$layout() {
        return top$LAYOUT;
    }

    private static final long top$OFFSET = 20;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int top
     * }
     */
    public static final long top$offset() {
        return top$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int top
     * }
     */
    public static int top(MemorySegment struct) {
        return struct.get(top$LAYOUT, top$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int top
     * }
     */
    public static void top(MemorySegment struct, int fieldValue) {
        struct.set(top$LAYOUT, top$OFFSET, fieldValue);
    }

    private static final OfInt right$LAYOUT = (OfInt)$LAYOUT.select(groupElement("right"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int right
     * }
     */
    public static final OfInt right$layout() {
        return right$LAYOUT;
    }

    private static final long right$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int right
     * }
     */
    public static final long right$offset() {
        return right$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int right
     * }
     */
    public static int right(MemorySegment struct) {
        return struct.get(right$LAYOUT, right$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int right
     * }
     */
    public static void right(MemorySegment struct, int fieldValue) {
        struct.set(right$LAYOUT, right$OFFSET, fieldValue);
    }

    private static final OfInt bottom$LAYOUT = (OfInt)$LAYOUT.select(groupElement("bottom"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int bottom
     * }
     */
    public static final OfInt bottom$layout() {
        return bottom$LAYOUT;
    }

    private static final long bottom$OFFSET = 28;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int bottom
     * }
     */
    public static final long bottom$offset() {
        return bottom$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int bottom
     * }
     */
    public static int bottom(MemorySegment struct) {
        return struct.get(bottom$LAYOUT, bottom$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int bottom
     * }
     */
    public static void bottom(MemorySegment struct, int fieldValue) {
        struct.set(bottom$LAYOUT, bottom$OFFSET, fieldValue);
    }

    private static final OfInt layout$LAYOUT = (OfInt)$LAYOUT.select(groupElement("layout"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int layout
     * }
     */
    public static final OfInt layout$layout() {
        return layout$LAYOUT;
    }

    private static final long layout$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int layout
     * }
     */
    public static final long layout$offset() {
        return layout$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int layout
     * }
     */
    public static int layout(MemorySegment struct) {
        return struct.get(layout$LAYOUT, layout$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int layout
     * }
     */
    public static void layout(MemorySegment struct, int fieldValue) {
        struct.set(layout$LAYOUT, layout$OFFSET, fieldValue);
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}

