package srp14.replace.type.code.with.subclasses.work

class Employee(var type: JobTitle) {
    // ...
    var monthlySalary: Int = 0

    fun payAmount(): Int = type.payAmount(this)
}

abstract class JobTitle {
    abstract fun payAmount(employee: Employee): Int
}

class Engineer : JobTitle() {
    override fun payAmount(employee: Employee): Int =
        employee.monthlySalary
}

class Salesman(var commission: Int = 0) : JobTitle() {
    override fun payAmount(employee: Employee): Int =
        employee.monthlySalary + commission
}

class Manager(var bonus: Int = 0) : JobTitle() {
    override fun payAmount(employee: Employee): Int =
        employee.monthlySalary + bonus
}

