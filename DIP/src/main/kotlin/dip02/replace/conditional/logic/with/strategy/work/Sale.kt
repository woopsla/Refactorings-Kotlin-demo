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

class Sale {
    var discountStrategy: DiscountStrategy = DiscountStrategy.createNoDiscountStrategy()
    var total = 0.0

    private val items = mutableListOf<Item>()

    fun addItem(item: Item) {
        items.add(item)
        total += item.subTotal
    }

    fun totalWithDiscount(): Double = discountStrategy.totalWithDiscount(this)
}
