/*
 * SMELL: Meaningless Name
 * 
 * TREATMENT: Use Intention-revealing Name
 */
package scp01.use.intention.revealing.name.work

import java.util.*

// this function returns discount rate depending on the season of the year
fun getValue(m: Int): Float { // m: month
    var r = 0.0f // r: discountRate
    if (m >= 3 && m <= 5) r = 0.2f
    else if (m >= 6 && m <= 8) r = 0.5f
    else if (m >= 9 && m <= 11) r = 0.2f
    else r = 0.1f
    return r
}

fun main(args: Array<String>) {
    println("Discount Rate: " + getValue(Scanner(System.`in`).nextInt()))
}
