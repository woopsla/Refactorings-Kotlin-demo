/*
 * 0. Self-encapsulate type field
 * 1. Define abstract Strategy base class
 *    1.1 define abstract type code getter method
 *       - each subclass will return extant symbolic constants
 * 	  1.2 define static create method using type code (or may define
 * 	      separate create methods for each type)
 * 2. Connect host class and strategy subclasses by changing type code to
 *    strategy and modifying access methods for type code and constructors.
 * 3. Modify setters using factory method.
 * 4. Modify the name of accessors to clarify returning type code
 *    (Accessors return type code, yet)
 * 5. Move symbolic constants to hierarchy.
 * 6. Replace conditional with polymorphism.
 */
package dip02.replace.conditional.logic.with.strategy.work

import dip02.replace.conditional.logic.with.strategy.Item
import dip02.replace.conditional.logic.with.strategy.ThresholdData

class Sale(var discountCode: Int = NO_DISCOUNT) {
    var percentage: Double = 0.0
    var thresholdData: ThresholdData? = null

    private val items = mutableListOf<Item>()
    val total get() = items.sumOf { it.subTotal }

    fun addItem(item: Item) {
        items.add(item)
    }

    fun totalWithDiscount(): Double = total - getDiscount()

    fun getDiscount(): Double {
        return when (discountCode) {
            NO_DISCOUNT -> 0.0
            PERCENT_DISCOUNT -> total * percentage
            THRESHOLD_DISCOUNT ->
                thresholdData?.let {
                    if (total > it.thresholdAmount) it.discountAmount else 0.0
                } ?: throw IllegalStateException("Threshold data is not set")

            else -> throw IllegalStateException("Invalid discount code")
        }
    }

    companion object {
        const val NO_DISCOUNT: Int = 1
        const val PERCENT_DISCOUNT: Int = 2
        const val THRESHOLD_DISCOUNT: Int = 3
    }
}
