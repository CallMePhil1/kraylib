package kraylib.raylib.collections

import kraylib.raylib.NativeMemory
import kraylib.raylib.ext.allocateFillZero
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

val arena: Arena = Arena.ofConfined()

private class TestNativeMemory(
    memorySegment: MemorySegment = arena.allocateFillZero(12),
) : NativeMemory(memorySegment) {

    var x: Int
        get() = memorySegment.get(ValueLayout.JAVA_INT, 0)
        set(value) = memorySegment.set(ValueLayout.JAVA_INT, 0, value)

    var y: Int
        get() = memorySegment.get(ValueLayout.JAVA_INT, 4)
        set(value) = memorySegment.set(ValueLayout.JAVA_INT, 4, value)

    var z: Int
        get() = memorySegment.get(ValueLayout.JAVA_INT, 8)
        set(value) = memorySegment.set(ValueLayout.JAVA_INT, 8, value)

    override fun toString() = "[TestNativeMemory] Address: ${memorySegment.address()} X: $x, Y: $y, Z: $z"
}

class NativeListTest {
    @Test
    fun `GIVEN a list of native memory items WHEN nativeListOf is called THEN the items should be made contiguous and wrapped in a NativeList`() {
        val testList = (0..10).map {
            TestNativeMemory().apply {
                x = it
                y = it * 2
                z = it * 3
            }
        }
        val nativeList = nativeListOf(testList, ::TestNativeMemory)

        nativeList.forEachIndexed { index, item ->
            assertEquals(item.x, index)
            assertEquals(item.y, index * 2)
            assertEquals(item.z, index * 3)
        }
    }

    @Test
    fun `GIVEN varargs of native memory items WHEN nativeListOf is called THEN the items should be made contiguous and wrapped in a NativeList`() {
        val item1 = TestNativeMemory().apply {
            x = 0
            y = 0
            z = 0
        }
        val item2 = TestNativeMemory().apply {
            x = 1
            y = 2
            z = 3
        }
        val item3 = TestNativeMemory().apply {
            x = 2
            y = 4
            z = 6
        }
        val item4 = TestNativeMemory().apply {
            x = 3
            y = 6
            z = 9
        }

        val nativeList = nativeListOf(::TestNativeMemory, item1, item2, item3, item4)

        nativeList.forEachIndexed { index, item ->
            assertEquals(item.x, index)
            assertEquals(item.y, index * 2)
            assertEquals(item.z, index * 3)
        }
    }

    @Test
    fun `GIVEN a list of native memory items WHEN mutableNativeListOf is called THEN the items should be made contiguous and wrapped in a MutableNativeList`() {
        val testList = (0..10).map {
            TestNativeMemory().apply {
                x = it
                y = it * 2
                z = it * 3
            }
        }

        val nativeList = mutableNativeListOf(testList, ::TestNativeMemory)

        nativeList.forEachIndexed { index, item ->
            assertEquals(item.x, index)
            assertEquals(item.y, index * 2)
            assertEquals(item.z, index * 3)
        }
    }

    @Test
    fun `GIVEN a MutableNativeList WHEN adding an element to the list THEN the list should grow to fit new element and add element to end of list`() {
        val testList = (0..10).map {
            TestNativeMemory().apply {
                x = it
                y = it * 2
                z = it * 3
            }
        }

        val nativeList = mutableNativeListOf(testList, ::TestNativeMemory)

        nativeList.forEachIndexed { index, item ->
            assertEquals(item.x, index)
            assertEquals(item.y, index * 2)
            assertEquals(item.z, index * 3)
        }

        val testNativeMemory = TestNativeMemory().apply {
            x = 100
            y = 200
            z = 300
        }
        nativeList.add(testNativeMemory)

        val addedNativeMemory = nativeList[nativeList.size - 1]

        assertEquals(addedNativeMemory, testNativeMemory)
        assertEquals(addedNativeMemory.address, testNativeMemory.address)
    }

    @Test
    fun `GIVEN a MutableNativeList WHEN adding a collection of element to the list THEN the list should grow to fit new element and add element to end of list`() {

        val testList = (0..10).map {
            TestNativeMemory().apply {
                x = it
                y = it * 2
                z = it * 3
            }
        }

        val nativeList = mutableNativeListOf(testList, ::TestNativeMemory)

        nativeList.forEachIndexed { index, item ->
            assertEquals(item.x, index)
            assertEquals(item.y, index * 2)
            assertEquals(item.z, index * 3)
        }

        val testNativeMemory = TestNativeMemory().apply {
            x = 100
            y = 200
            z = 300
        }
        val testNativeMemory2 = TestNativeMemory().apply {
            x = 1000
            y = 2000
            z = 3000
        }
        val testNativeMemory3 = TestNativeMemory().apply {
            x = 10000
            y = 20000
            z = 30000
        }
        nativeList.addAll(listOf(testNativeMemory, testNativeMemory2, testNativeMemory3))

        val addedNativeMemory = nativeList[nativeList.size - 3]
        val addedNativeMemory2 = nativeList[nativeList.size - 2]
        val addedNativeMemory3 = nativeList[nativeList.size - 1]

        assertEquals(addedNativeMemory, testNativeMemory)
        assertEquals(addedNativeMemory.address, testNativeMemory.address)

        assertEquals(addedNativeMemory2, testNativeMemory2)
        assertEquals(addedNativeMemory2.address, testNativeMemory2.address)

        assertEquals(addedNativeMemory3, testNativeMemory3)
        assertEquals(addedNativeMemory3.address, testNativeMemory3.address)
    }

    @Test
    fun `GIVEN a MutableNativeList WHEN adding a collection of elements at an index THEN the list should grow to fit new element and add element to end of list`() {

        val testList = (0..10).map {
            TestNativeMemory().apply {
                x = it
                y = it * 2
                z = it * 3
            }
        }

        val nativeList = mutableNativeListOf(testList, ::TestNativeMemory)

        nativeList.forEachIndexed { index, item ->
            assertEquals(item.x, index)
            assertEquals(item.y, index * 2)
            assertEquals(item.z, index * 3)
        }

        val testNativeMemory = TestNativeMemory().apply {
            x = 100
            y = 200
            z = 300
        }
        val testNativeMemory2 = TestNativeMemory().apply {
            x = 1000
            y = 2000
            z = 3000
        }
        val testNativeMemory3 = TestNativeMemory().apply {
            x = 10000
            y = 20000
            z = 30000
        }
        nativeList.addAll(5, listOf(testNativeMemory, testNativeMemory2, testNativeMemory3))

        val addedNativeMemory = nativeList[5]
        val addedNativeMemory2 = nativeList[6]
        val addedNativeMemory3 = nativeList[7]

        assertEquals(addedNativeMemory, testNativeMemory)
        assertEquals(addedNativeMemory.address, testNativeMemory.address)

        assertEquals(addedNativeMemory2, testNativeMemory2)
        assertEquals(addedNativeMemory2.address, testNativeMemory2.address)

        assertEquals(addedNativeMemory3, testNativeMemory3)
        assertEquals(addedNativeMemory3.address, testNativeMemory3.address)
    }

    @Test
    fun `GIVEN a MutableNativeList WHEN removing an element from the list THEN the list should remove the element and shift the remaining elements left`() {
        val testList = (0..10).map {
            TestNativeMemory().apply {
                x = it
                y = it * 2
                z = it * 3
            }
        }

        val nativeList = mutableNativeListOf(testList, ::TestNativeMemory)

        nativeList.forEachIndexed { index, item ->
            assertEquals(item.x, index)
            assertEquals(item.y, index * 2)
            assertEquals(item.z, index * 3)
        }

        val testTestMemory = TestNativeMemory()
        testTestMemory.x = 6
        testTestMemory.y = 12
        testTestMemory.z = 18

        nativeList.removeAt(5)

        assertEquals(nativeList[5], testTestMemory)
    }

    @Test
    fun `GIVEN a MutableNativeList WHEN removing an element from the list THEN the item should be found in the list and removed`() {

        val testList = (0..10).map {
            TestNativeMemory().apply {
                x = it
                y = it * 2
                z = it * 3
            }
        }

        val nativeList = mutableNativeListOf(testList, ::TestNativeMemory)

        nativeList.forEachIndexed { index, item ->
            assertEquals(item.x, index)
            assertEquals(item.y, index * 2)
            assertEquals(item.z, index * 3)
        }

        val toBeRemoved = TestNativeMemory()
        toBeRemoved.x = 1
        toBeRemoved.y = 2
        toBeRemoved.z = 3

        assertTrue(nativeList.remove(toBeRemoved))

        val shiftElement1 = nativeList[1]
        val shiftElement2 = nativeList[2]

        assertEquals(2, shiftElement1.x)
        assertEquals(4, shiftElement1.y)
        assertEquals(6, shiftElement1.z)

        assertEquals(3, shiftElement2.x)
        assertEquals(6, shiftElement2.y)
        assertEquals(9, shiftElement2.z)
    }
}