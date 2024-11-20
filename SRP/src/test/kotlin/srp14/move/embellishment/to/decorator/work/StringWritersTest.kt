package srp14.move.embellishment.to.decorator.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StringWritersTest {
    private lateinit var writer: Writer

    @Test
    fun should_not_capitalize_if_that_policy_not_set() {
        writer = StringWriter(isCapitalized = false)
        writer.write("not capitalized")

        assertEquals("not capitalized", writer.toString())
    }

    @Test
    fun should_capitalize_if_that_policy_was_set() {
        writer = StringWriter(isCapitalized = true)
        writer.write("capitalized")

        assertEquals("CAPITALIZED", writer.toString())
    }
}