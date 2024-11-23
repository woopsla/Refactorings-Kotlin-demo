package dip04.replace.sate.conditional.with.state.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CarTest {
    // SUT
    private lateinit var car: Car

    @BeforeEach
    fun setup() {
        car = Car()
    }

    @Test
    fun `should speed up in normal mode`() {
        // Arrange (Given)
        // Act (When)
        car.speedUp(100)

        // Assert (Then)
        assertEquals(100, car.speed)
    }

    @Test
    fun `should not speed up in limp mode`() {
        // Arrange (Given)
        car.engineFaultDetected()

        // Act (When)
        car.speedUp(100)

        // Assert (Then)
        assertEquals(Car.LIMP_MODE_MAX_SPEED, car.speed)
    }

    @Test
    fun `should speed up after engine repaired`() {
        // Arrange (Given)
        car.engineFaultDetected()
        car.engineRepaired()

        // Act (When)
        car.speedUp(100)

        // Assert (Then)
        assertEquals(100, car.speed)
    }

    @Test
    fun `should speed down in normal node`() {
        // Arrange (Given)
        car.speedUp(100)

        // Act (When)
        car.speedDown(50)

        // Assert (Then)
        assertEquals(50, car.speed)
    }

    @Test
    fun `should speed down in limp node`() {
        // Arrange (Given)
        car.speedUp(100)
        car.engineFaultDetected()

        // Act (When)
        car.speedDown(30)

        // Assert (Then)
        assertEquals(30, car.speed)
    }
}