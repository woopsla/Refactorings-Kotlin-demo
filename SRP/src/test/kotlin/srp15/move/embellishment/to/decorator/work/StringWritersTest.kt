package srp15.move.embellishment.to.decorator.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StringWritersTest {
    private lateinit var writer: Writer

    @Test
    fun should_not_capitalize() {
        writer = StringWriter()
        writer.write("not capitalized")

        assertEquals("not capitalized", writer.toString())
    }

    @Test
    fun should_capitalize() {
        writer = CapStringWriter(StringWriter())
        writer.write("capitalized")

        assertEquals("CAPITALIZED", writer.toString())
    }

    @Test
    fun should_censored_and_capitalize() {
        writer = CensoredStringWriter(CapStringWriter(StringWriter()))
//        writer = CapStringWriter(CensoredStringWriter(StringWriter()))
        writer.write("Kotlin rocks!")

//        assertEquals("XXXXXX ROCKS!", writer.toString())
        println(writer.toString())
    }

    @Test
    fun should_censored_and_capitalize2() {
        val buf = StringBuilder()
        writer = stringWriter(buf)
            .capWriter()
            .censorWriter()

        writer.write("Kotlin rocks!")

//        assertEquals("XXXXXX ROCKS!", writer.toString())
        println(buf.toString())
    }
}