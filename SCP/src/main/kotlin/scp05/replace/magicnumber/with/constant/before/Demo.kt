/*
 * SMELL: Literal (or Magic Number)
 *   -- You have a literal number with a particular meaning.
 *   -- Magic numbers are really nasty when you need to reference 
 *      the same logical number in more than one place 
 *      ==> cause Shotgun Surgery smell
 * 
 * TREATMENT: Replace Magic Number with Symbolic Constant
 *   -- Replace the literal with a constant with meaningful name
 */
package scp05.replace.magicnumber.with.constant.before

fun potentialEnergy(mass: Double, height: Double): Double {
    return mass * height * 9.81 // Gravitational Constant
}
