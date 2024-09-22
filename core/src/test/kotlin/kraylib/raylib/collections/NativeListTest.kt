package kraylib.raylib.collections

import kraylib.raylib.NativeMemory
import org.junit.jupiter.api.Assertions.*
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import kotlin.test.Test

val arena: Arena = Arena.ofConfined()

private class TestNativeMemory(test: Int) : NativeMemory<TestNativeMemory>() {
    override var memorySegment: MemorySegment = arena.allocate(ValueLayout.JAVA_INT)

    var test: Int
        get() = memorySegment.get(ValueLayout.JAVA_INT, 0)
        set(value) = memorySegment.set(ValueLayout.JAVA_INT, 0, value)

    override fun copy(): TestNativeMemory = TestNativeMemory(this.test)

    init {
        this.test = test
    }
}

class NativeListTest {
    @Test
    fun `GIVEN a list of native memory items AND updateRefs is true WHEN nativeListOf is called THEN all instance should have updated memory segments but same values`() {
        val testList = (0..10).map {
            TestNativeMemory(it)
        }
        val nativeList = NativeList.nativeListOf(testList, ValueLayout.JAVA_INT, updateRefs = true)

        testList.forEachIndexed { index, item ->
            assertEquals(nativeList[index].memorySegment.address(), item.memorySegment.address())
            assertEquals(nativeList[index].test, item.test)
        }
    }

    @Test
    fun `GIVEN a list of native memory items AND updateRefs is false WHEN nativeListOf is called THEN all instance should have different memory segments but same values`() {
        val testList = (0..10).map {
            TestNativeMemory(it)
        }
        val nativeList = NativeList.nativeListOf(testList, ValueLayout.JAVA_INT, updateRefs = false)

        testList.forEachIndexed { index, item ->
            assertNotEquals(nativeList[index].memorySegment.address(), item.memorySegment.address())
            assertEquals(nativeList[index].test, item.test)
        }
    }
}