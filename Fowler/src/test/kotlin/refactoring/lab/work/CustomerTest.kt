package refactoring.lab.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CustomerTest {
    private val customer = Customer("John Doe", "2000-01-01")
    private val video = Video("The Matrix", Rating.FIFTEEN, PriceCode.REGULAR)
    private val newVideo = Video("Witch III", Rating.EIGHTEEN, PriceCode.NEW_RELEASE)

    @Test
    fun `rent a video should increase rental count by one`() {
        // Arrange (Given)

        // Act (When)
        customer.rentVideo(video)

        // Assert (Then)
        assertEquals(1, customer.rentals.size)
    }

    @Test
    fun `video return should decrement rental count by one`() {
        // Arrange (Given)
        customer.rentVideo(video)

        // Act (When)
        customer.returnVideo(video.title, LocalDate.now().plusDays(1))

        // Assert (Then)
        assertEquals(0, customer.rentals.size)
        assertEquals(1, customer.returnedRentals.size)
    }

    @Test
    fun `test statement for regular rental`() {
        val expected = """
            Rental Record for John Doe
            	The Matrix	3.5
            Amount owed is 3.5
            You earned 1 frequent renter points
        """.trimIndent()

        // Arrange (Given)
        customer.rentVideo(video, LocalDate.now())
        customer.returnVideo(video.title, LocalDate.now().plusDays(3))

        // Act (When)
        val actual = customer.statement()

        // Assert (Then)
        assertEquals(expected, actual)
    }

    @Test
    fun `test statement for new-release rental`() {
        val expected = """
            Rental Record for John Doe
            	Witch III	9.0
            Amount owed is 9.0
            You earned 2 frequent renter points
        """.trimIndent()

        // Arrange (Given)
        customer.rentVideo(newVideo, LocalDate.now())
        customer.returnVideo(newVideo.title, LocalDate.now().plusDays(3))

        // Act (When)
        val actual = customer.statement()

        // Assert (Then)
        assertEquals(expected, actual)
    }

    @Test
    fun `should throw exception if renter is underage`() {
        // Arrange (Given)
        val customer = Customer("John Doe", "2010-01-01")

        // Assert (Then)
        assertThrows(CustomerUnderageException::class.java) {
            // Act (When)
            customer.rentVideo(video, LocalDate.of(2024, 11, 27))
        }
    }
}