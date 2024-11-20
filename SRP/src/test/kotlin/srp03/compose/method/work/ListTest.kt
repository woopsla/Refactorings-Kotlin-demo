package srp03.compose.method.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ListTest {
    private val ints = List<Int>()

    @Test
    fun add_first_element_increment_capacity_to_10() {
        addElements(1)

        assertEquals(1, ints.size)
        assertEquals(10, ints.capacity)
    }

    @Test
    fun add_first_10_elements_returns_capacity_of_10() {
        addElements(10)

        assertEquals(10, ints.size)
        assertEquals(10, ints.capacity)
    }


    @Test
    fun add_first_11_elements_returns_capacity_of_20() {
        addElements(11)

        assertEquals(11, ints.size)
        assertEquals(20, ints.capacity)
    }

    private fun addElements(n: Int) {
        for (i in 0..<n) ints.add(i)
    }
}
