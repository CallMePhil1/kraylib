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
 * struct Camera2D {
 *     Vector2 offset;
 *     Vector2 target;
 *     float rotation;
 *     float zoom;
 * }
 * }
 */
public class Camera2D {

    Camera2D() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        Vector2.layout().withName("offset"),
        Vector2.layout().withName("target"),
        Raylib.C_FLOAT.withName("rotation"),
        Raylib.C_FLOAT.withName("zoom")
    ).withName("Camera2D");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final GroupLayout offset$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("offset"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Vector2 offset
     * }
     */
    public static final GroupLayout offset$layout() {
        return offset$LAYOUT;
    }

    private static final long offset$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Vector2 offset
     * }
     */
    public static final long offset$offset() {
        return offset$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Vector2 offset
     * }
     */
    public static MemorySegment offset(MemorySegment struct) {
        return struct.asSlice(offset$OFFSET, offset$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Vector2 offset
     * }
     */
    public static void offset(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, offset$OFFSET, offset$LAYOUT.byteSize());
    }

    private static final GroupLayout target$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("target"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Vector2 target
     * }
     */
    public static final GroupLayout target$layout() {
        return target$LAYOUT;
    }

    private static final long target$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Vector2 target
     * }
     */
    public static final long target$offset() {
        return target$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Vector2 target
     * }
     */
    public static MemorySegment target(MemorySegment struct) {
        return struct.asSlice(target$OFFSET, target$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Vector2 target
     * }
     */
    public static void target(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, target$OFFSET, target$LAYOUT.byteSize());
    }

    private static final OfFloat rotation$LAYOUT = (OfFloat)$LAYOUT.select(groupElement("rotation"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float rotation
     * }
     */
    public static final OfFloat rotation$layout() {
        return rotation$LAYOUT;
    }

    private static final long rotation$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float rotation
     * }
     */
    public static final long rotation$offset() {
        return rotation$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float rotation
     * }
     */
    public static float rotation(MemorySegment struct) {
        return struct.get(rotation$LAYOUT, rotation$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float rotation
     * }
     */
    public static void rotation(MemorySegment struct, float fieldValue) {
        struct.set(rotation$LAYOUT, rotation$OFFSET, fieldValue);
    }

    private static final OfFloat zoom$LAYOUT = (OfFloat)$LAYOUT.select(groupElement("zoom"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float zoom
     * }
     */
    public static final OfFloat zoom$layout() {
        return zoom$LAYOUT;
    }

    private static final long zoom$OFFSET = 20;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float zoom
     * }
     */
    public static final long zoom$offset() {
        return zoom$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float zoom
     * }
     */
    public static float zoom(MemorySegment struct) {
        return struct.get(zoom$LAYOUT, zoom$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float zoom
     * }
     */
    public static void zoom(MemorySegment struct, float fieldValue) {
        struct.set(zoom$LAYOUT, zoom$OFFSET, fieldValue);
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

