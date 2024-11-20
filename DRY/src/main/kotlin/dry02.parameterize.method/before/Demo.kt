/*
 * SMELL: Similar Codes among Methods
 *   -- Several methods do similar things but with different values
 * 
 * TREATMENT: Parameterize Method
 *   -- Create one method that uses a parameter for the different values
 */
package dry02.parameterize.method.before

class Client

class Employee {
    private var type = 0
    private var salary = 0.0
    var yearsOfExperience: Int = 0
    val clients: List<Client> = mutableListOf()

    // ...
    fun promoteToManager() {
        type = MANAGER
        salary *= 1.5
    }

    fun tenPercentRaise() {
        salary *= 1.1
    }

    fun fivePercentRaise() {
        salary *= 1.05
    }

    companion object {
        var MANAGER: Int = 1
    }
}

fun main(args: Array<String>) {
    // Somewhere in client code
    val employee = Employee()

    if (employee.yearsOfExperience > 5) {
        if (employee.clients.size > 10) {
            employee.promoteToManager()
        } else {
            employee.fivePercentRaise()
        }
    }
}
