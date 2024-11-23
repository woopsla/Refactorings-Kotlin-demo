/*
 * SMELL: Data Clump
 *   -- Your methods contain a repeating group of parameters.
 * 
 * TREATMENT: Introduce Parameter Object
 *   -- Replace these parameters with an object.
 */
package coupling01.introduce.parameter.`object`.before

import java.time.LocalDate

data class Transaction(val value: Double, val date: LocalDate)

class Account {
    // ...
    private val transactions = mutableListOf<Transaction>()

    fun getFlowBetween(start: LocalDate, end: LocalDate): Double {
        var result = 0.0

        for (each in transactions) {
            if (each.date >= start && each.date <= end) {
                result += each.value
            }
        }
        return result
    }

    fun add(transaction: Transaction) {
        transactions.add(transaction)
    }
}
