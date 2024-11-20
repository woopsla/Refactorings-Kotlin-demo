/*
 * SMELL: Primitive Obsession
 *   -- A class has a field that contains type code. 
 *      The values of this type are not used in operator conditions 
 *      and do not affect the behavior of the program.
 *
 * What are the shortcomings of this approach?
 *
 * 1) The field setters often do not check which value is sent,
 * which can cause big problems when someone sends unintended or wrong values
 * to these fields.
 *
 * 2) In addition, type verification is impossible for these fields.
 * It is possible to send any number or string to them, which won't be type
 * checked by your IDE and even allow your program to run (and crash later).
 *
 * TREATMENT: Replace Type Code with Object
 *   -- Create a new class and use its objects instead of the type code values.
 */
package srp13.replace.type.code.with.classes.before

class Person(var bloodGroup: Int? = null) {

    companion object {
        /*
	     * Create a new BloodGroup class encapsulating a code,
	     * and make clients use its instances instead of code directly.
	     */
        const val O: Int = 0
        const val A: Int = 1
        const val B: Int = 2
        const val AB: Int = 3
    }
}

fun main(args: Array<String>) {
    // Somewhere in client code.
    val parent = Person(Person.O)
    if (parent.bloodGroup == Person.AB) {
        // todo
    }

    val child = Person()
    child.bloodGroup = parent.bloodGroup
}

/*
 * 1. Create a new BloodGroup Class, 
 *    which will be responsible for blood types. 
 * 2. Place the blood type code from the Person class, 
 *    its getter and the constructor, which initialize the field value.
 * 3. Create static methods for each type code value from the original class. 
 *    These methods should return instances of the BloodGroup class.
 * 4. In the original class, change the type of code field to BloodGroup.
 * 5. Change the code of the setter and constructor accordingly.
 * 6. Then change the field getter so that it calls the getter of the BloodGroup.
 * 7. Replace all type code values with calls to the corresponding static methods 
 *    of the type class.
 * 8. Get rid of direct references to constants of the Person class 
 *    in the remaining code. We can use calls to the methods of 
 *    the BloodGroup class instead.
 * 9. In the end, it is better to avoid using any numeric codes for BloodGroup 
 *    and use objects instead. Let's try to do so in the Person class.
 * 10. You can remove unused constants from the Person class.
 * 11. Finally, you should make the BloodGroup constructor private.
 */

