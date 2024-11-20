package refactoring.lab.before

import java.time.LocalDate

class Rental(val video: Video, val occurredOn: LocalDate, var daysRented: Int = 0) {
    fun returnVideo(on: LocalDate) {
        daysRented = (on.toEpochDay() - occurredOn.toEpochDay()).toInt()
    }
}
