package srp13.replace.type.code.with.classes.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun testTwoPersonsWithSameGroup() {
        val tom = Person(BloodGroup.A)
        val jerry = Person(BloodGroup.A)

        assertTrue(tom.bloodGroup == jerry.bloodGroup)
    }

    @Test
    fun testTwoPersonsWithDifferentGroups() {
        val tom = Person(BloodGroup.A)
        val jerry = Person(BloodGroup.B)

        assertTrue(tom.bloodGroup != jerry.bloodGroup)
    }
}
