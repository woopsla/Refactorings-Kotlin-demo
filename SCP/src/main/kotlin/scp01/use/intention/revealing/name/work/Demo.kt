/*
 * SMELL: Meaningless Name
 * 
 * TREATMENT: Use Intention-revealing Name
 */
package scp01.use.intention.revealing.name.work

import java.util.*

// this function returns discount rate depending on the season of the year
fun getDiscountRate(month: Int): Float { // m: month
    var discountRate = 0.0f // r: discountRate
    if (month >= 3 && month <= 5) discountRate = 0.2f
    else if (month >= 6 && month <= 8) discountRate = 0.5f
    else if (month >= 9 && month <= 11) discountRate = 0.2f
    else discountRate = 0.1f
    return discountRate
}

fun main(args: Array<String>) {
    println("Discount Rate: " + getDiscountRate(Scanner(System.`in`).nextInt()))
}
