package kraylib.raylib

import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.groupElement
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

private val arena: Arena = Arena.ofConfined()

class SimpleNativeMemory : NativeMemory(arena.allocate(layout)) {
    private val testLayout = layout.select(groupElement("test")) as ValueLayout.OfInt
    private val test2Layout = layout.select(groupElement("test2")) as ValueLayout.OfInt

    var test: Int
        get() = memorySegment.get(testLayout, 0)
        set(value) = memorySegment.set(testLayout, 0, value)

    var test2: Int
        get() = memorySegment.get(test2Layout, 4)
        set(value) = memorySegment.set(test2Layout, 4, value)

    companion object {
        val layout: StructLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("test"),
            ValueLayout.JAVA_INT.withName("test2")
        ).withName("SimpleNativeMemory")
    }
}

class ComplexNativeMemory(
    simpleNativeMemory: SimpleNativeMemory
) : NativeMemory(arena.allocate(layout)) {
    private val testLayout = layout.select(groupElement("test")) as ValueLayout.OfInt
    private val test2Layout = layout.select(groupElement("test2")) as ValueLayout.OfInt

    var simpleNativeMemory: SimpleNativeMemory = simpleNativeMemory
        set(value) {
            MemorySegment.copy(value.memorySegment, 0, simpleNativeMemory.memorySegment, 0, SimpleNativeMemory.layout.byteSize())
        }

    var test: Int
        get() = memorySegment.get(testLayout, 0)
        set(value) = memorySegment.set(testLayout, 0, value)

    var test2: Int
        get() = memorySegment.get(test2Layout, 4)
        set(value) = memorySegment.set(test2Layout, 4, value)

    init {
        this.simpleNativeMemory = simpleNativeMemory
    }

    companion object {
        val layout: StructLayout = MemoryLayout.structLayout(
            SimpleNativeMemory.layout.withName("simple"),
            ValueLayout.JAVA_INT.withName("test"),
            ValueLayout.JAVA_INT.withName("test2"),
        ).withName("ComplexNativeMemory")
    }
}

class NativeMemoryTest {
    @Test
    fun `GIVEN a simple class that inherits NativeMemory WHEN modifying a field THEN it should modify the off heap memory`() {
        val testInt = 10

        val simpleNativeMemory = SimpleNativeMemory()

        assertEquals(simpleNativeMemory.test, 0)

        simpleNativeMemory.test = testInt

        assertEquals(simpleNativeMemory.test, testInt)
    }

    @Test
    fun `GIVEN a complex class that inherits NativeMemory WHEN modifying a field THEN it should modify the off heap memory`() {
        val simpleNativeMemory = SimpleNativeMemory()
        simpleNativeMemory.test = 10
        simpleNativeMemory.test2 = 20

        val complexNativeMemory = ComplexNativeMemory(
            simpleNativeMemory
        )
        complexNativeMemory.test = 100
        complexNativeMemory.test2 = 200

        assertEquals(simpleNativeMemory.test, 10)
        assertEquals(simpleNativeMemory.test2, 20)
        assertEquals(complexNativeMemory.simpleNativeMemory.test, 10)
        assertEquals(complexNativeMemory.simpleNativeMemory.test2, 20)
        assertEquals(complexNativeMemory.test, 100)
        assertEquals(complexNativeMemory.test2, 200)

        complexNativeMemory.simpleNativeMemory.test = 300
        complexNativeMemory.simpleNativeMemory.test2 = 400

        assertEquals(simpleNativeMemory.test, 300)
        assertEquals(simpleNativeMemory.test2, 400)
        assertEquals(complexNativeMemory.simpleNativeMemory.test, 300)
        assertEquals(complexNativeMemory.simpleNativeMemory.test2, 400)

        val newSimple = SimpleNativeMemory()
        newSimple.test = 1111
        newSimple.test2 = 2222

        complexNativeMemory.simpleNativeMemory = newSimple

        assertEquals(1111, complexNativeMemory.simpleNativeMemory.test)
        assertEquals(2222, complexNativeMemory.simpleNativeMemory.test2)

        assertNotEquals(complexNativeMemory.simpleNativeMemory.address, newSimple.address)
    }

    @Test
    fun `GIVEN two native memory WHEN comparing equality THEN it should succeed if content matches and fail if not`() {
        val simpleNativeMemory = SimpleNativeMemory()
        simpleNativeMemory.test = 10
        simpleNativeMemory.test2 = 20

        val simpleNativeMemory2 = SimpleNativeMemory()
        simpleNativeMemory2.test = 10
        simpleNativeMemory2.test2 = 20

        val simpleNativeMemory3 = SimpleNativeMemory()
        simpleNativeMemory3.test = 10
        simpleNativeMemory3.test2 = 213

        assertEquals(simpleNativeMemory, simpleNativeMemory2)
        assertNotEquals(simpleNativeMemory, simpleNativeMemory3)
    }
}