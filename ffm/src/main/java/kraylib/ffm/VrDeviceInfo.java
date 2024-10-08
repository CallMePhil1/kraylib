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
 * struct VrDeviceInfo {
 *     int hResolution;
 *     int vResolution;
 *     float hScreenSize;
 *     float vScreenSize;
 *     float vScreenCenter;
 *     float eyeToScreenDistance;
 *     float lensSeparationDistance;
 *     float interpupillaryDistance;
 *     float lensDistortionValues[4];
 *     float chromaAbCorrection[4];
 * }
 * }
 */
public class VrDeviceInfo {

    VrDeviceInfo() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        Raylib.C_INT.withName("hResolution"),
        Raylib.C_INT.withName("vResolution"),
        Raylib.C_FLOAT.withName("hScreenSize"),
        Raylib.C_FLOAT.withName("vScreenSize"),
        Raylib.C_FLOAT.withName("vScreenCenter"),
        Raylib.C_FLOAT.withName("eyeToScreenDistance"),
        Raylib.C_FLOAT.withName("lensSeparationDistance"),
        Raylib.C_FLOAT.withName("interpupillaryDistance"),
        MemoryLayout.sequenceLayout(4, Raylib.C_FLOAT).withName("lensDistortionValues"),
        MemoryLayout.sequenceLayout(4, Raylib.C_FLOAT).withName("chromaAbCorrection")
    ).withName("VrDeviceInfo");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt hResolution$LAYOUT = (OfInt)$LAYOUT.select(groupElement("hResolution"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int hResolution
     * }
     */
    public static final OfInt hResolution$layout() {
        return hResolution$LAYOUT;
    }

    private static final long hResolution$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int hResolution
     * }
     */
    public static final long hResolution$offset() {
        return hResolution$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int hResolution
     * }
     */
    public static int hResolution(MemorySegment struct) {
        return struct.get(hResolution$LAYOUT, hResolution$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int hResolution
     * }
     */
    public static void hResolution(MemorySegment struct, int fieldValue) {
        struct.set(hResolution$LAYOUT, hResolution$OFFSET, fieldValue);
    }

    private static final OfInt vResolution$LAYOUT = (OfInt)$LAYOUT.select(groupElement("vResolution"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int vResolution
     * }
     */
    public static final OfInt vResolution$layout() {
        return vResolution$LAYOUT;
    }

    private static final long vResolution$OFFSET = 4;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int vResolution
     * }
     */
    public static final long vResolution$offset() {
        return vResolution$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int vResolution
     * }
     */
    public static int vResolution(MemorySegment struct) {
        return struct.get(vResolution$LAYOUT, vResolution$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int vResolution
     * }
     */
    public static void vResolution(MemorySegment struct, int fieldValue) {
        struct.set(vResolution$LAYOUT, vResolution$OFFSET, fieldValue);
    }

    private static final OfFloat hScreenSize$LAYOUT = (OfFloat)$LAYOUT.select(groupElement("hScreenSize"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float hScreenSize
     * }
     */
    public static final OfFloat hScreenSize$layout() {
        return hScreenSize$LAYOUT;
    }

    private static final long hScreenSize$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float hScreenSize
     * }
     */
    public static final long hScreenSize$offset() {
        return hScreenSize$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float hScreenSize
     * }
     */
    public static float hScreenSize(MemorySegment struct) {
        return struct.get(hScreenSize$LAYOUT, hScreenSize$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float hScreenSize
     * }
     */
    public static void hScreenSize(MemorySegment struct, float fieldValue) {
        struct.set(hScreenSize$LAYOUT, hScreenSize$OFFSET, fieldValue);
    }

    private static final OfFloat vScreenSize$LAYOUT = (OfFloat)$LAYOUT.select(groupElement("vScreenSize"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float vScreenSize
     * }
     */
    public static final OfFloat vScreenSize$layout() {
        return vScreenSize$LAYOUT;
    }

    private static final long vScreenSize$OFFSET = 12;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float vScreenSize
     * }
     */
    public static final long vScreenSize$offset() {
        return vScreenSize$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float vScreenSize
     * }
     */
    public static float vScreenSize(MemorySegment struct) {
        return struct.get(vScreenSize$LAYOUT, vScreenSize$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float vScreenSize
     * }
     */
    public static void vScreenSize(MemorySegment struct, float fieldValue) {
        struct.set(vScreenSize$LAYOUT, vScreenSize$OFFSET, fieldValue);
    }

    private static final OfFloat vScreenCenter$LAYOUT = (OfFloat)$LAYOUT.select(groupElement("vScreenCenter"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float vScreenCenter
     * }
     */
    public static final OfFloat vScreenCenter$layout() {
        return vScreenCenter$LAYOUT;
    }

    private static final long vScreenCenter$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float vScreenCenter
     * }
     */
    public static final long vScreenCenter$offset() {
        return vScreenCenter$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float vScreenCenter
     * }
     */
    public static float vScreenCenter(MemorySegment struct) {
        return struct.get(vScreenCenter$LAYOUT, vScreenCenter$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float vScreenCenter
     * }
     */
    public static void vScreenCenter(MemorySegment struct, float fieldValue) {
        struct.set(vScreenCenter$LAYOUT, vScreenCenter$OFFSET, fieldValue);
    }

    private static final OfFloat eyeToScreenDistance$LAYOUT = (OfFloat)$LAYOUT.select(groupElement("eyeToScreenDistance"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float eyeToScreenDistance
     * }
     */
    public static final OfFloat eyeToScreenDistance$layout() {
        return eyeToScreenDistance$LAYOUT;
    }

    private static final long eyeToScreenDistance$OFFSET = 20;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float eyeToScreenDistance
     * }
     */
    public static final long eyeToScreenDistance$offset() {
        return eyeToScreenDistance$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float eyeToScreenDistance
     * }
     */
    public static float eyeToScreenDistance(MemorySegment struct) {
        return struct.get(eyeToScreenDistance$LAYOUT, eyeToScreenDistance$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float eyeToScreenDistance
     * }
     */
    public static void eyeToScreenDistance(MemorySegment struct, float fieldValue) {
        struct.set(eyeToScreenDistance$LAYOUT, eyeToScreenDistance$OFFSET, fieldValue);
    }

    private static final OfFloat lensSeparationDistance$LAYOUT = (OfFloat)$LAYOUT.select(groupElement("lensSeparationDistance"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float lensSeparationDistance
     * }
     */
    public static final OfFloat lensSeparationDistance$layout() {
        return lensSeparationDistance$LAYOUT;
    }

    private static final long lensSeparationDistance$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float lensSeparationDistance
     * }
     */
    public static final long lensSeparationDistance$offset() {
        return lensSeparationDistance$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float lensSeparationDistance
     * }
     */
    public static float lensSeparationDistance(MemorySegment struct) {
        return struct.get(lensSeparationDistance$LAYOUT, lensSeparationDistance$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float lensSeparationDistance
     * }
     */
    public static void lensSeparationDistance(MemorySegment struct, float fieldValue) {
        struct.set(lensSeparationDistance$LAYOUT, lensSeparationDistance$OFFSET, fieldValue);
    }

    private static final OfFloat interpupillaryDistance$LAYOUT = (OfFloat)$LAYOUT.select(groupElement("interpupillaryDistance"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float interpupillaryDistance
     * }
     */
    public static final OfFloat interpupillaryDistance$layout() {
        return interpupillaryDistance$LAYOUT;
    }

    private static final long interpupillaryDistance$OFFSET = 28;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float interpupillaryDistance
     * }
     */
    public static final long interpupillaryDistance$offset() {
        return interpupillaryDistance$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float interpupillaryDistance
     * }
     */
    public static float interpupillaryDistance(MemorySegment struct) {
        return struct.get(interpupillaryDistance$LAYOUT, interpupillaryDistance$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float interpupillaryDistance
     * }
     */
    public static void interpupillaryDistance(MemorySegment struct, float fieldValue) {
        struct.set(interpupillaryDistance$LAYOUT, interpupillaryDistance$OFFSET, fieldValue);
    }

    private static final SequenceLayout lensDistortionValues$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("lensDistortionValues"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float lensDistortionValues[4]
     * }
     */
    public static final SequenceLayout lensDistortionValues$layout() {
        return lensDistortionValues$LAYOUT;
    }

    private static final long lensDistortionValues$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float lensDistortionValues[4]
     * }
     */
    public static final long lensDistortionValues$offset() {
        return lensDistortionValues$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float lensDistortionValues[4]
     * }
     */
    public static MemorySegment lensDistortionValues(MemorySegment struct) {
        return struct.asSlice(lensDistortionValues$OFFSET, lensDistortionValues$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float lensDistortionValues[4]
     * }
     */
    public static void lensDistortionValues(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, lensDistortionValues$OFFSET, lensDistortionValues$LAYOUT.byteSize());
    }

    private static long[] lensDistortionValues$DIMS = { 4 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * float lensDistortionValues[4]
     * }
     */
    public static long[] lensDistortionValues$dimensions() {
        return lensDistortionValues$DIMS;
    }
    private static final VarHandle lensDistortionValues$ELEM_HANDLE = lensDistortionValues$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * float lensDistortionValues[4]
     * }
     */
    public static float lensDistortionValues(MemorySegment struct, long index0) {
        return (float)lensDistortionValues$ELEM_HANDLE.get(struct, 0L, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * float lensDistortionValues[4]
     * }
     */
    public static void lensDistortionValues(MemorySegment struct, long index0, float fieldValue) {
        lensDistortionValues$ELEM_HANDLE.set(struct, 0L, index0, fieldValue);
    }

    private static final SequenceLayout chromaAbCorrection$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("chromaAbCorrection"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * float chromaAbCorrection[4]
     * }
     */
    public static final SequenceLayout chromaAbCorrection$layout() {
        return chromaAbCorrection$LAYOUT;
    }

    private static final long chromaAbCorrection$OFFSET = 48;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * float chromaAbCorrection[4]
     * }
     */
    public static final long chromaAbCorrection$offset() {
        return chromaAbCorrection$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * float chromaAbCorrection[4]
     * }
     */
    public static MemorySegment chromaAbCorrection(MemorySegment struct) {
        return struct.asSlice(chromaAbCorrection$OFFSET, chromaAbCorrection$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * float chromaAbCorrection[4]
     * }
     */
    public static void chromaAbCorrection(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, chromaAbCorrection$OFFSET, chromaAbCorrection$LAYOUT.byteSize());
    }

    private static long[] chromaAbCorrection$DIMS = { 4 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * float chromaAbCorrection[4]
     * }
     */
    public static long[] chromaAbCorrection$dimensions() {
        return chromaAbCorrection$DIMS;
    }
    private static final VarHandle chromaAbCorrection$ELEM_HANDLE = chromaAbCorrection$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * float chromaAbCorrection[4]
     * }
     */
    public static float chromaAbCorrection(MemorySegment struct, long index0) {
        return (float)chromaAbCorrection$ELEM_HANDLE.get(struct, 0L, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * float chromaAbCorrection[4]
     * }
     */
    public static void chromaAbCorrection(MemorySegment struct, long index0, float fieldValue) {
        chromaAbCorrection$ELEM_HANDLE.set(struct, 0L, index0, fieldValue);
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

