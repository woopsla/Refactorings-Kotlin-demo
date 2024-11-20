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
    if (month in 3..5)  // Spring
        0.2f
    else if (month in 6..8)  // Summer
        0.5f
    else if (month in 9..11)  // Fall
        0.2f
    else // Winter
        0.1f

fun main(args: Array<String>) {
    println("Discount Rate: " + getDiscountRate(Scanner(System.`in`).nextInt()))
}
