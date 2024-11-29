package refactoring.lab.work

import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class Video(val title: String, val rating: Rating, val priceCode: PriceCode) {

    fun canBeCheckedOutFor(dateOfBirth: String): Boolean {
        try {
            val formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.getDefault())

            val birthDay = LocalDate.parse(dateOfBirth, formatter)
            val age = ChronoUnit.YEARS.between(birthDay, LocalDate.now()).toInt()

            return when (rating) {
                Rating.TWELVE -> age >= 12
                Rating.FIFTEEN -> age >= 15
                Rating.EIGHTEEN -> age >= 18
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return false
    }

    fun getCharge(daysRented: Int): Double = priceCode.getCharge(daysRented)
    fun getFrequentRentalPoint(daysRented: Int): Int = priceCode.getFrequentRentalPoint(daysRented)
}

abstract class PriceCode {
    abstract fun getCharge(daysRented: Int): Double
    open fun getFrequentRentalPoint(daysRented: Int): Int = 1

    companion object {
        val REGULAR = RegularPrice()
        val NEW_RELEASE = NewReleasePrice()
        val CHILDREN = ChildrenPrice()
    }
}

class RegularPrice : PriceCode() {
    override fun getCharge(daysRented: Int): Double =
        if (daysRented > 2) 2.0 + (daysRented - 2) * 1.5 else 2.0
}

class NewReleasePrice : PriceCode() {
    override fun getCharge(daysRented: Int): Double = daysRented * 3.0
    override fun getFrequentRentalPoint(daysRented: Int): Int = if (daysRented > 1) 2 else 1
}

class ChildrenPrice : PriceCode() {
    override fun getCharge(daysRented: Int): Double =
        if (daysRented > 3) 1.5 + (daysRented - 3) * 1.5 else 1.5
}