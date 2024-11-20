package srp13.replace.type.code.with.classes.before

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun testTwoPersonsWithSameGroup() {
        val tom = Person(Person.A)
        val jerry = Person(Person.A)

        assertTrue(tom.bloodGroup == jerry.bloodGroup)
    }

    @Test
    fun testTwoPersonsWithDifferentGroups() {
        val tom = Person(Person.A)
        val jerry = Person(Person.B)

        assertTrue(tom.bloodGroup != jerry.bloodGroup)
    }
}
