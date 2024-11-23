package dry04.form.template.method.before

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.BufferedReader

class CSVReaderTest {

    @MockK(relaxed = true)
    lateinit var bufferedReader: BufferedReader

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test csvProductReader`() {
        every { bufferedReader.readLine() } returnsMany listOf("1,tv,100", "2,radio,50", null)

        // Arrange (Given)
        val expected = """
            Product(id=1, description=tv, price=100)
            Product(id=2, description=radio, price=50)
        """.trimIndent()
        val csvReader = ProductCSVReader()

        // Act (When)
        val actual = csvReader.getAll(bufferedReader)

        // Assert (Then)
        assertEquals(expected, actual.joinToString(separator = "\n") { it.toString() })
    }

    @Test
    fun `test csvCustomerReader`() {
        every { bufferedReader.readLine() } returnsMany listOf("John,33,010-111-2222,Pennsylvania", "Peter,25,010-333-4444,California", null)

        // Arrange (Given)
        val expected = """
            Customer(name=John, age=33, phone=010-111-2222, address=Pennsylvania)
            Customer(name=Peter, age=25, phone=010-333-4444, address=California)
        """.trimIndent()
        val csvReader = CustomerCSVReader()

        // Act (When)
        val actual = csvReader.getAll(bufferedReader)

        // Assert (Then)
        assertEquals(expected, actual.joinToString(separator = "\n") { it.toString() })
    }
}