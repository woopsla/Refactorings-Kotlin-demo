package srp02.decompose.conditional.before

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class StadiumTest {

    @Test
    fun testGetTicketPrice() {
        val stadium = Stadium()

        assertEquals(2.2, stadium.getTicketPrice(LocalDate.of(2024, 6, 30), 1))
        assertEquals(2.2, stadium.getTicketPrice(LocalDate.of(2024, 9, 1), 1))
        assertEquals(0.1, stadium.getTicketPrice(LocalDate.of(2024, 7, 1), 1))
    }
}