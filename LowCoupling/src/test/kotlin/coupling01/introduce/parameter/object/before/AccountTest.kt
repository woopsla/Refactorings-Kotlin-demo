package coupling01.introduce.parameter.`object`.before

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class AccountTest {
    @Test
    fun should_return_sum_of_transactions_in_range() {
        val account = Account()
        account.add(Transaction(100.0, LocalDate.of(2024, 5, 15)))
        account.add(Transaction(200.0, LocalDate.of(2024, 6, 15)))
        account.add(Transaction(300.0, LocalDate.of(2024, 7, 15)))

        val value =
            account.getFlowBetween(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 7, 30))

        assertEquals(500.0, value, 0.1)
    }
}