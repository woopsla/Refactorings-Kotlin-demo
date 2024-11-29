/*
 * SMELL: Data Clump
 *   -- Your methods contain a repeating group of parameters.
 * 
 * TREATMENT: Introduce Parameter Object
 *   -- Replace these parameters with an object.
 */
package coupling01.introduce.parameter.`object`.work

import java.time.LocalDate

data class Transaction(val value: Double, val date: LocalDate)

class Account {
    // ...
    private val transactions = mutableListOf<Transaction>()

    fun getFlowBetween(duration: Duration): Double =
        transactions
            .filter { duration.containedIn(it.date) }
            .sumOf { it.value }

    fun add(transaction: Transaction) {
        transactions.add(transaction)
    }
}

data class Duration(val start: LocalDate, val end: LocalDate) {
    fun containedIn(
        date: LocalDate,
    ): Boolean = date >= start && date <= end
}
