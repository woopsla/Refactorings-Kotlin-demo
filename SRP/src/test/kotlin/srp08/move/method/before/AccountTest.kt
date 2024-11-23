package srp08.move.method.before

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AccountTest {
    // SUT
    private lateinit var account: Account

    @Test
    fun `charged with no overdrawn charge added if not overdrawn`() {
        // Arrange (Given)
        account = Account(AccountType())

        // Act (When)
        val actual = account.bankCharge()

        // Assert (Then)
        assertEquals(4.5, actual)
    }

    @Test
    fun `charged with non-VIP overdrawn if overdrawn`() {
        // Arrange (Given)
        account = Account(AccountType())
        account.daysOverdrawn = 5

        // Act (When)
        val actual = account.bankCharge()

        // Assert (Then)
        assertEquals(4.5 + 5 * 1.75, actual)
    }

    @Test
    fun `charged with VIP overdrawn if overdrawn less than 8`() {
        // Arrange (Given)
        account = Account(AccountType(true))
        account.daysOverdrawn = 5

        // Act (When)
        val actual = account.bankCharge()

        // Assert (Then)
        assertEquals(4.5 + 10.0, actual)
    }

    @Test
    fun `charged with VIP overdrawn if overdrawn greater than 7`() {
        // Arrange (Given)
        account = Account(AccountType(true))
        account.daysOverdrawn = 10

        // Act (When)
        val actual = account.bankCharge()

        // Assert (Then)
        assertEquals(4.5 + 10.0 + (10 - 7) * 0.85, actual)
    }


}