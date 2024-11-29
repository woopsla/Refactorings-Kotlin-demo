/*
 * SMELL: Complicated (Conditional) Expression
 *   -- You have an expression that is not clear hard to understand.
 * 
 * TREATMENT: Extract Variable (aka Introduce Explaining Variable)
 *   -- Put the result of the expression or part of the expression 
 *      in a temporary variable with a name that explains the purpose.
 *   -- Extract method(or Replace Temp with Query) can be considered.
 */
package scp02.extract.variable.work

import java.util.*

// discountRate depends on the season of the year
fun getDiscountRate(month: Int): Float =
    when {
        isSpring(month) -> 0.2f
        isSummer(month) -> 0.5f
        isFall(month) -> 0.2f
        else -> 0.1f
    }

private fun isFall(month: Int): Boolean = month in 9..11
private fun isSummer(month: Int): Boolean = month in 6..8
private fun isSpring(month: Int): Boolean = month in 3..5

fun main(args: Array<String>) {
    println("Discount Rate: " + getDiscountRate(Scanner(System.`in`).nextInt()))
}
