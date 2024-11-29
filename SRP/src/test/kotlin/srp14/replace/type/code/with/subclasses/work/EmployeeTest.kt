package srp14.replace.type.code.with.subclasses.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EmployeeTest {
    // SUT
    private lateinit var employee: Employee

    @Test
    fun `should return engineer pay amount`() {
        // Arrange (Given)
        employee = Employee(Engineer()).apply {
            monthlySalary = 1000
        }

        // Act (When)
        val payAmount = employee.payAmount()

        // Assert (Then)
        assertEquals(1000, payAmount)
    }

    @Test
    fun `should return saleman pay amount`() {
        // Arrange (Given)
        employee = Employee(Salesman(commission = 50)).apply {
            monthlySalary = 1000
        }

        // Act (When)
        val payAmount = employee.payAmount()

        // Assert (Then)
        assertEquals(1050, payAmount)
    }

    @Test
    fun `should return manager pay amount`() {
        // Arrange (Given)
        employee = Employee(Manager(bonus = 100)).apply {
            monthlySalary = 1000
        }

        // Act (When)
        val payAmount = employee.payAmount()

        // Assert (Then)
        assertEquals(1100, payAmount)
    }

}