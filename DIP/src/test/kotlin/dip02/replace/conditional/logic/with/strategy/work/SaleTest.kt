package dip02.replace.conditional.logic.with.strategy.work

import dip02.replace.conditional.logic.with.strategy.Item
import dip02.replace.conditional.logic.with.strategy.ThresholdData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SalesTest {
    private lateinit var sale: Sale

    @BeforeEach
    fun setup() {
        sale = Sale()
    }

    @Test
    fun should_return_zero_when_no_items_in_sale() {
        // Given
        // When
        val total = sale.totalWithDiscount()

        // Then
        assertEquals(0.0, total, 0.1)
    }

    @Test
    @Throws(Exception::class)
    fun should_return_total_without_deduction_when_default_discount_policy() {
        // Given
        sale.addItem(Item(1, 100.0))
        sale.addItem(Item(2, 50.0))

        // When
        val total = sale.totalWithDiscount()

        // Then
        assertEquals(200.0, total, 0.1)
    }

    @Test
    fun should_always_return_total_with_deduction_when_percent_discount_policy() {
        // Given
        sale.discountCode = Sale.PERCENT_DISCOUNT
        sale.percentage = 0.1
        sale.addItem(Item(1, 100.0))
        sale.addItem(Item(2, 50.0))

        // When
        val total = sale.totalWithDiscount()

        // Then
        assertEquals(180.0, total, 0.1)
    }

    @Test
    fun should_return_total_without_deduction_if_total_is_below_threshold_when_threshold_policy() {
        // Given
        sale.discountCode = Sale.THRESHOLD_DISCOUNT
        sale.thresholdData = ThresholdData(300.0, 20.0)
        sale.addItem(Item(1, 100.0))
        sale.addItem(Item(2, 50.0))

        // When
        val total = sale.totalWithDiscount()

        // Then
        assertEquals(200.0, total, 0.1)
    }
}
