package refactoring.lab.before

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

class VideoTests {
    companion object {
        const val UNUSED_NAME: String = ""
        const val UNUSED_TITLE: String = ""
    }

    @Test
    fun `customer must be over twelve to rent a video rated twelve`() {
        // get current date
        val age = LocalDate.now().year - 12
        println("age: $age")
        val dateOfBirth = String.format("%s-01-01", age)

        val customer = Customer(UNUSED_NAME, dateOfBirth)
        val video = Video(UNUSED_TITLE, Rating.TWELVE, Video.NEW_RELEASE)
        video.rentFor(customer, LocalDate.now())
    }

    @Test
    fun `video rented by customer of legal age is added to customers rented videos`() {
        val customer = Customer(UNUSED_NAME, "1964-01-01")
        val video = Video(UNUSED_TITLE, Rating.TWELVE, Video.NEW_RELEASE)
        video.rentFor(customer, LocalDate.now())
        assertTrue(customer.rentedVideos.contains(video))
    }
}
