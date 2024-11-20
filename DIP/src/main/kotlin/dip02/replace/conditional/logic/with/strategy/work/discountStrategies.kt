package dip02.replace.conditional.logic.with.strategy.work

import dip02.replace.conditional.logic.with.strategy.ThresholdData

abstract class DiscountStrategy {
    abstract fun totalWithDiscount(sale: Sale): Double

    companion object {
        @JvmStatic
        fun createNoDiscountStrategy(): DiscountStrategy = NoDiscountStrategy()
        fun createPercentageDiscountStrategy(percentage: Double): DiscountStrategy =
            PercentageDiscountStrategy(percentage)

        fun createThresholdDiscountStrategy(thresholdData: ThresholdData): DiscountStrategy =
            ThresholdDiscountStrategy(thresholdData)
    }
}

class NoDiscountStrategy : DiscountStrategy() {
    override fun totalWithDiscount(sale: Sale): Double {
        return sale.total
    }
}

class PercentageDiscountStrategy(private val percentage: Double) : DiscountStrategy() {
    override fun totalWithDiscount(sale: Sale): Double {
        sale.total -= sale.total * percentage
        return sale.total
    }
}

class ThresholdDiscountStrategy(private val thresholdData: ThresholdData) : DiscountStrategy() {
    override fun totalWithDiscount(sale: Sale): Double {
        if (sale.total > thresholdData.thresholdAmount) sale.total -= thresholdData.discountAmount
        return sale.total
    }
}
