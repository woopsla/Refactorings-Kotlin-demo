package org.scarlet.demo.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EmailAddressTest {

    @Test
    fun valid_input() {
        val input = "kotlin@gmail.com"
        val emailAddress: EmailAddress = EmailAddress.parse(input)

        assertEquals(EmailAddress("kotlin", "gmail.com"), emailAddress)
    }

    @Test
    fun invalid_input() {
        val input = "kotlin@"

        assertThrows(IllegalArgumentException::class.java) {
            EmailAddress.parse(input)
        }
    }
}