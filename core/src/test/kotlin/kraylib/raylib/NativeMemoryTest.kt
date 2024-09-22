package kraylib.raylib

import kraylib.raylib.SimpleNativeMemory.Companion
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.groupElement
import java.nio.ByteOrder
import kotlin.test.Test
import kotlin.test.assertEquals

private val arena: Arena = Arena.ofConfined()

class SimpleNativeMemory : NativeMemory<SimpleNativeMemory>() {
    override var memorySegment: MemorySegment = arena.allocate(layout)
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
) : NativeMemory<ComplexNativeMemory>() {

    override var memorySegment = arena.allocate(layout)
    private val testLayout = layout.select(groupElement("test")) as ValueLayout.OfInt
    private val test2Layout = layout.select(groupElement("test2")) as ValueLayout.OfInt
    private val test3Layout = layout.select(groupElement("test3")) as ValueLayout.OfInt

    var simpleNativeMemory: SimpleNativeMemory = simpleNativeMemory
        set(value) {
            MemorySegment.copy(value.memorySegment, 0, memorySegment, 0, SimpleNativeMemory.layout.byteSize())
            field = value
        }

    var test: Int
        get() = memorySegment.get(testLayout, 0)
        set(value) = memorySegment.set(testLayout, 0, value)

    var test2: Int
        get() = memorySegment.get(test2Layout, 4)
        set(value) = memorySegment.set(test2Layout, 4, value)

    var test3: Int
        get() = memorySegment.get(test3Layout, 8)
        set(value) = memorySegment.set(test3Layout, 8, value)

    init {
        this.simpleNativeMemory = simpleNativeMemory
    }

    companion object {
        val layout: StructLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("test"),
            ValueLayout.JAVA_INT.withName("test2"),
            ValueLayout.JAVA_INT.withName("test3")
        ).withName("ComplexNativeMemory")
    }
}

class NativeMemoryTest {
    @Test
    fun `GIVEN a simple class that inherits NativeMemory WHEN modifing a field THEN it should modify the off heap memory`() {
        val testInt = 10

        val simpleNativeMemory = SimpleNativeMemory()

        assertEquals(simpleNativeMemory.test, 0)

        simpleNativeMemory.test = testInt

        assertEquals(simpleNativeMemory.test, testInt)
    }

    @Test
    fun `GIVEN a complex class that inherits NativeMemory WHEN modifing a field THEN it should modify the off heap memory`() {
        val simpleNativeMemory = SimpleNativeMemory()
        simpleNativeMemory.test = 10
        simpleNativeMemory.test2 = 20

        val complexNativeMemory = ComplexNativeMemory(
            simpleNativeMemory
        )

        complexNativeMemory.test3 = 30

        println(complexNativeMemory.test)
        println(complexNativeMemory.test2)
        println(complexNativeMemory.test3)

        val complexBuffer = complexNativeMemory.memorySegment.asByteBuffer()

        println(complexBuffer.getInt(0))
        println(complexBuffer.getInt(1))
        println(complexBuffer.getInt(5))

//        assertEquals(complexBuffer.getInt(0), 0)
        assertEquals(complexBuffer.getInt(1), 10)
        assertEquals(complexBuffer.getLong(2), 20)

        assertEquals(complexNativeMemory.simpleNativeMemory.test, 10)
        assertEquals(simpleNativeMemory.test, 0)
    }
}