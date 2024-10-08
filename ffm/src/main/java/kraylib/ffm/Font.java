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
 * struct Font {
 *     int baseSize;
 *     int glyphCount;
 *     int glyphPadding;
 *     Texture2D texture;
 *     Rectangle *recs;
 *     GlyphInfo *glyphs;
 * }
 * }
 */
public class Font {

    Font() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        Raylib.C_INT.withName("baseSize"),
        Raylib.C_INT.withName("glyphCount"),
        Raylib.C_INT.withName("glyphPadding"),
        Texture.layout().withName("texture"),
        Raylib.C_POINTER.withName("recs"),
        Raylib.C_POINTER.withName("glyphs")
    ).withName("Font");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt baseSize$LAYOUT = (OfInt)$LAYOUT.select(groupElement("baseSize"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int baseSize
     * }
     */
    public static final OfInt baseSize$layout() {
        return baseSize$LAYOUT;
    }

    private static final long baseSize$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int baseSize
     * }
     */
    public static final long baseSize$offset() {
        return baseSize$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int baseSize
     * }
     */
    public static int baseSize(MemorySegment struct) {
        return struct.get(baseSize$LAYOUT, baseSize$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int baseSize
     * }
     */
    public static void baseSize(MemorySegment struct, int fieldValue) {
        struct.set(baseSize$LAYOUT, baseSize$OFFSET, fieldValue);
    }

    private static final OfInt glyphCount$LAYOUT = (OfInt)$LAYOUT.select(groupElement("glyphCount"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int glyphCount
     * }
     */
    public static final OfInt glyphCount$layout() {
        return glyphCount$LAYOUT;
    }

    private static final long glyphCount$OFFSET = 4;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int glyphCount
     * }
     */
    public static final long glyphCount$offset() {
        return glyphCount$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int glyphCount
     * }
     */
    public static int glyphCount(MemorySegment struct) {
        return struct.get(glyphCount$LAYOUT, glyphCount$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int glyphCount
     * }
     */
    public static void glyphCount(MemorySegment struct, int fieldValue) {
        struct.set(glyphCount$LAYOUT, glyphCount$OFFSET, fieldValue);
    }

    private static final OfInt glyphPadding$LAYOUT = (OfInt)$LAYOUT.select(groupElement("glyphPadding"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int glyphPadding
     * }
     */
    public static final OfInt glyphPadding$layout() {
        return glyphPadding$LAYOUT;
    }

    private static final long glyphPadding$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int glyphPadding
     * }
     */
    public static final long glyphPadding$offset() {
        return glyphPadding$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int glyphPadding
     * }
     */
    public static int glyphPadding(MemorySegment struct) {
        return struct.get(glyphPadding$LAYOUT, glyphPadding$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int glyphPadding
     * }
     */
    public static void glyphPadding(MemorySegment struct, int fieldValue) {
        struct.set(glyphPadding$LAYOUT, glyphPadding$OFFSET, fieldValue);
    }

    private static final GroupLayout texture$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("texture"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Texture2D texture
     * }
     */
    public static final GroupLayout texture$layout() {
        return texture$LAYOUT;
    }

    private static final long texture$OFFSET = 12;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Texture2D texture
     * }
     */
    public static final long texture$offset() {
        return texture$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Texture2D texture
     * }
     */
    public static MemorySegment texture(MemorySegment struct) {
        return struct.asSlice(texture$OFFSET, texture$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Texture2D texture
     * }
     */
    public static void texture(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, texture$OFFSET, texture$LAYOUT.byteSize());
    }

    private static final AddressLayout recs$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("recs"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * Rectangle *recs
     * }
     */
    public static final AddressLayout recs$layout() {
        return recs$LAYOUT;
    }

    private static final long recs$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * Rectangle *recs
     * }
     */
    public static final long recs$offset() {
        return recs$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * Rectangle *recs
     * }
     */
    public static MemorySegment recs(MemorySegment struct) {
        return struct.get(recs$LAYOUT, recs$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * Rectangle *recs
     * }
     */
    public static void recs(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(recs$LAYOUT, recs$OFFSET, fieldValue);
    }

    private static final AddressLayout glyphs$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("glyphs"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * GlyphInfo *glyphs
     * }
     */
    public static final AddressLayout glyphs$layout() {
        return glyphs$LAYOUT;
    }

    private static final long glyphs$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * GlyphInfo *glyphs
     * }
     */
    public static final long glyphs$offset() {
        return glyphs$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * GlyphInfo *glyphs
     * }
     */
    public static MemorySegment glyphs(MemorySegment struct) {
        return struct.get(glyphs$LAYOUT, glyphs$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * GlyphInfo *glyphs
     * }
     */
    public static void glyphs(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(glyphs$LAYOUT, glyphs$OFFSET, fieldValue);
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

