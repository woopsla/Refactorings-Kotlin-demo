package srp14.replace.type.code.with.subclasses.before

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EmployeeTest {
    // SUT
    private lateinit var employee: Employee

    @BeforeEach
    fun setUp() {
        employee = Employee(Employee.MANAGER).apply {
            monthlySalary = 1000
            commission = 50
            bonus = 100
        }
    }

    @Test
    fun `should return engineer pay amount`() {
        // Arrange (Given)
        employee.type = Employee.ENGINEER

        // Act (When)
        val payAmount = employee.payAmount()

        // Assert (Then)
        assertEquals(1000, payAmount)
    }

    @Test
    fun `should return saleman pay amount`() {
        // Arrange (Given)
        employee.type = Employee.SALESMAN

        // Act (When)
        val payAmount = employee.payAmount()

        // Assert (Then)
        assertEquals(1050, payAmount)
    }

    @Test
    fun `should return manager pay amount`() {
        // Arrange (Given)
        employee.type = Employee.MANAGER

        // Act (When)
        val payAmount = employee.payAmount()

        // Assert (Then)
        assertEquals(1100, payAmount)
    }

}