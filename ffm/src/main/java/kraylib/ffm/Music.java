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
 * struct Music {
 *     AudioStream stream;
 *     unsigned int frameCount;
 *     _Bool looping;
 *     int ctxType;
 *     void *ctxData;
 * }
 * }
 */
public class Music {

    Music() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        AudioStream.layout().withName("stream"),
        Raylib.C_INT.withName("frameCount"),
        Raylib.C_BOOL.withName("looping"),
        MemoryLayout.paddingLayout(3),
        Raylib.C_INT.withName("ctxType"),
        MemoryLayout.paddingLayout(4),
        Raylib.C_POINTER.withName("ctxData")
    ).withName("Music");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final GroupLayout stream$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("stream"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * AudioStream stream
     * }
     */
    public static final GroupLayout stream$layout() {
        return stream$LAYOUT;
    }

    private static final long stream$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * AudioStream stream
     * }
     */
    public static final long stream$offset() {
        return stream$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * AudioStream stream
     * }
     */
    public static MemorySegment stream(MemorySegment struct) {
        return struct.asSlice(stream$OFFSET, stream$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * AudioStream stream
     * }
     */
    public static void stream(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, stream$OFFSET, stream$LAYOUT.byteSize());
    }

    private static final OfInt frameCount$LAYOUT = (OfInt)$LAYOUT.select(groupElement("frameCount"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int frameCount
     * }
     */
    public static final OfInt frameCount$layout() {
        return frameCount$LAYOUT;
    }

    private static final long frameCount$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int frameCount
     * }
     */
    public static final long frameCount$offset() {
        return frameCount$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int frameCount
     * }
     */
    public static int frameCount(MemorySegment struct) {
        return struct.get(frameCount$LAYOUT, frameCount$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int frameCount
     * }
     */
    public static void frameCount(MemorySegment struct, int fieldValue) {
        struct.set(frameCount$LAYOUT, frameCount$OFFSET, fieldValue);
    }

    private static final OfBoolean looping$LAYOUT = (OfBoolean)$LAYOUT.select(groupElement("looping"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * _Bool looping
     * }
     */
    public static final OfBoolean looping$layout() {
        return looping$LAYOUT;
    }

    private static final long looping$OFFSET = 36;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * _Bool looping
     * }
     */
    public static final long looping$offset() {
        return looping$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * _Bool looping
     * }
     */
    public static boolean looping(MemorySegment struct) {
        return struct.get(looping$LAYOUT, looping$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * _Bool looping
     * }
     */
    public static void looping(MemorySegment struct, boolean fieldValue) {
        struct.set(looping$LAYOUT, looping$OFFSET, fieldValue);
    }

    private static final OfInt ctxType$LAYOUT = (OfInt)$LAYOUT.select(groupElement("ctxType"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int ctxType
     * }
     */
    public static final OfInt ctxType$layout() {
        return ctxType$LAYOUT;
    }

    private static final long ctxType$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int ctxType
     * }
     */
    public static final long ctxType$offset() {
        return ctxType$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int ctxType
     * }
     */
    public static int ctxType(MemorySegment struct) {
        return struct.get(ctxType$LAYOUT, ctxType$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int ctxType
     * }
     */
    public static void ctxType(MemorySegment struct, int fieldValue) {
        struct.set(ctxType$LAYOUT, ctxType$OFFSET, fieldValue);
    }

    private static final AddressLayout ctxData$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("ctxData"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *ctxData
     * }
     */
    public static final AddressLayout ctxData$layout() {
        return ctxData$LAYOUT;
    }

    private static final long ctxData$OFFSET = 48;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *ctxData
     * }
     */
    public static final long ctxData$offset() {
        return ctxData$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *ctxData
     * }
     */
    public static MemorySegment ctxData(MemorySegment struct) {
        return struct.get(ctxData$LAYOUT, ctxData$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *ctxData
     * }
     */
    public static void ctxData(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(ctxData$LAYOUT, ctxData$OFFSET, fieldValue);
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

