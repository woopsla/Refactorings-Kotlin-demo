package srp10.extract.subclass.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import srp10.extract.subclass.Employee

class jobItemsTests {
    @Test
    fun should_calculate_total_price_according_to_job_types() {
        val kent = Employee(50)
        val j1 = jobItems(5, 0, true, kent)
        val j2 = jobItems(15, 10, false, null)
        val total = j1.totalPrice() + j2.totalPrice()

        assertEquals(400, total.toLong())
    }
}
