/*
 * SMELL: Method Chain
 *   -- The client gets object B from a field or method of object А. 
 *      Then the client calls a method of object B.
 * 
 * TREATMENT: Hide Delegate (Tell/Don't Ask, Law of Demeter, Law of Least knowledge)
 *   -- Create a new method in class A that delegates the call to object B. 
 *      Now the client does not know about, or depend on, class B.
 */
package coupling05.hide.delegate.work

class Person(val name: String, var department: Department? = null) {
    override fun toString(): String {
        return "Person(name='$name', department=${department?.name})"
    }
}

class Department(val name: String, var manager: Person? = null) {
    override fun toString(): String {
        return "Department(name='$name', manager=${manager?.name})"
    }
}

fun main(args: Array<String>) {
    // Somewhere in client code
    val john = Person("John")
    val department = Department("Engineering", john)
    john.department = department
    val peter = Person("Peter", department)

    val manager = peter.department?.manager
    println("Manager of Peter: $manager")
}