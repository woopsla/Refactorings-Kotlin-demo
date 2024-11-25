package srp14.replace.type.code.with.subclasses.work

class Employee(var type: Int) {
    // ...
    var monthlySalary: Int = 0
    var commission: Int = 0
    var bonus: Int = 0

    fun payAmount(): Int =
        when (type) {
            ENGINEER -> monthlySalary
            SALESMAN -> monthlySalary + commission
            MANAGER -> monthlySalary + bonus
            else -> throw RuntimeException("Incorrect Employee Code")
        }

    companion object {
        const val ENGINEER: Int = 0
        const val SALESMAN: Int = 1
        const val MANAGER: Int = 2
    }
}