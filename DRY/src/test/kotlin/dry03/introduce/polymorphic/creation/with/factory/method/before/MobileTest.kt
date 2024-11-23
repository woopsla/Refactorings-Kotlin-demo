package dry03.introduce.polymorphic.creation.with.factory.method.before

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MobileTest {
    @Test
    fun `should create toys from Seoul Factory`() {
        assertEquals(500.0, SeoulMobileFactory().produceMobile("Thunder").price, 0.0)
    }

    @Test
    fun `should create toys from Tokyo Factory`() {
        assertEquals(250.0, TokyoMobileFactory().of("Plus").price, 0.0)
    }
}