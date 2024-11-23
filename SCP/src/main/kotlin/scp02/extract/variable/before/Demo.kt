/*
 * SMELL: Complicated (Conditional) Expression
 *   -- You have an expression that is not clear hard to understand.
 * 
 * TREATMENT: Extract Variable (aka Introduce Explaining Variable)
 *   -- Put the result of the expression or part of the expression 
 *      in a temporary variable with a name that explains the purpose.
 *   -- Extract method(or Replace Temp with Query) can be considered.
 */
package scp02.extract.variable.before

import java.util.*

// discountRate depends on the season of the year
fun getDiscountRate(month: Int): Float =
    when (month) {
        in 3..5 -> 0.2f
        in 6..8 -> 0.5f
        in 9..11 -> 0.2f
        else -> 0.1f
    }

fun main(args: Array<String>) {
    println("Discount Rate: " + getDiscountRate(Scanner(System.`in`).nextInt()))
}
