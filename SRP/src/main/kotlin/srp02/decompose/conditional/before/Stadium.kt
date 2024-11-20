/*
 * SMELL: Complicated Conditional
 *   -- You have a complex conditional (if-then/else or switch).
 * 
 * TREATMENT: Decompose Conditional
 *   -- Decompose the complicated parts of the conditional 
 *   	into separate methods: the condition, then and else.
 *   -- May apply "Extract Method" (Make code clearer by 
 *   	decomposing it and replacing chunks of code with 
 *   	a method with meaningful name.)
 */
package srp02.decompose.conditional.before

import java.time.LocalDate

class Stadium {
    // ...
    private var summerRate: Double = 0.1
    private var winterRate: Double = 0.2
    private var winterServiceCharge: Double = 2.0

    fun getTicketPrice(date: LocalDate, quantity: Int): Double {
        val charge =
            if (date.isBefore(SUMMER_START) || date.isAfter(SUMMER_END)) { // not summer
                quantity * winterRate + winterServiceCharge
            } else {
                quantity * summerRate
            }
        return charge
    }

    companion object {
        private val SUMMER_START: LocalDate = LocalDate.of(2024, 7, 1)
        private val SUMMER_END: LocalDate = LocalDate.of(2024, 8, 31)
    }
}
