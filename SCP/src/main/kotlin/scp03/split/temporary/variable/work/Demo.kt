/*
 * SMELL: DD-Anomaly
 *   -- You have a local variable that is used to store various 
 *      intermediate values inside a method
 *      (except for loop or accumulator variables).
 * 
 * TREATMENT: Split Temporary Variables
 *   -- Use different variables for different values. 
 *      Each variable should be responsible for only one particular 
 *      thing (cohesion).
 */
package scp03.split.temporary.variable.work

fun printInfo(width: Int, height: Int) {
    val peripheral = 2 * (width + height)
    println(peripheral)

    val area = width * height
    println(area)
}

