/*
 * SMELL: Fat Interface
 *   -- Several clients use the same subset of a class's interface, 
 *      or two classes have part of their interfaces in common.
 * 
 * TREATMENT: Extract Interface
 *   -- Extract the subset into an interface.
 */
package coupling06.extract.interfaces.work

class TimeSheet {
    // ...
    fun charge(employee: Billable, days: Int): Double {
        val base = (employee.rate * days).toDouble()
        return if (employee.hasSpecialSkill()) {
            base * 1.05
        } else {
            base
        }
    }
}

class Employee(val name: String, var department: String) : Billable {
    override val rate: Int = 1

    override fun hasSpecialSkill(): Boolean {
        // ...
        return false
    }
}