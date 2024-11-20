package dry03.introduce.polymorphic.creation.with.factory.method.before

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MobileTest {
    @Test
    fun should_create_toys() {
        val seoulMobileFactory = SeoulMobileFactory()
        val product1 = seoulMobileFactory.produceMobile("Thunder")

        assertEquals(500.0, product1.price, 0.0)

        val tokyoMobileFactory = TokyoMobileFactory()
        val product2 = tokyoMobileFactory.of("Plus")

        assertEquals(250.0, product2.price, 0.0)
    }
}