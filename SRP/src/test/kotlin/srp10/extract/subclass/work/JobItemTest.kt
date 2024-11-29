package srp10.extract.subclass.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import srp10.extract.subclass.Employee

class jobItemTests {
    @Test
    fun should_calculate_total_price_according_to_job_types() {
        val kent = Employee(50)
        val j1 = LaborItem(5, kent)
        val j2 = PartItem(15, 10)
        val total = j1.totalPrice() + j2.totalPrice()

        assertEquals(400, total.toLong())
    }
}
