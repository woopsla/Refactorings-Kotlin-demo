package refactoring.lab.before

import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class Video(val title: String, val rating: Rating, val priceCode: Int) {

    companion object {
        const val REGULAR = 1
        const val NEW_RELEASE = 2
        const val CHILDREN = 3
    }

    fun rentFor(customer: Customer, occurredOn: LocalDate = LocalDate.now()) {
        if (isUnderAge(customer)) throw CustomerUnderageException
        customer.rentVideo(this, occurredOn)
    }

    private fun isUnderAge(customer: Customer): Boolean {
        try {
            val formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.getDefault())

            val birthDay = LocalDate.parse(customer.dateOfBirth, formatter)
            val age = ChronoUnit.YEARS.between(birthDay, LocalDate.now()).toInt()

            return when (rating) {
                Rating.TWELVE -> age < 12
                Rating.FIFTEEN -> age < 15
                Rating.EIGHTEEN -> age < 18
                else -> false
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return false
    }
}