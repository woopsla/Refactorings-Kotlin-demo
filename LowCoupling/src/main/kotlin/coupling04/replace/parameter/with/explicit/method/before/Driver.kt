/*
 * SMELL: Control Couple
 *   -- A method is split into parts, each of which is run 
 *      depending on the value of a parameter.
 * 
 * TREATMENT: Replace Parameter with Explicit Method
 *   -- Extract the individual parts of the method into their 
 *      own methods and call them instead of the original method.
 */
package coupling04.replace.parameter.with.explicit.method.before

class Item

class Order {
    var price = 0.0
    val items = mutableListOf<Item>()

    fun applyDiscount(type: Int, discount: Double) {
        price -= when (type) {
            FIXED_DISCOUNT -> discount
            PERCENT_DISCOUNT -> price * discount
            else -> throw IllegalArgumentException("Invalid discount type")
        }
    }

    companion object {
        // ...
        const val FIXED_DISCOUNT: Int = 0
        const val PERCENT_DISCOUNT: Int = 1
    }
}

object Driver {
    private var weekend = false

    @JvmStatic
    fun main(args: Array<String>) {
        // Somewhere in client code
        val order = Order()
        if (weekend) {
            order.applyDiscount(Order.FIXED_DISCOUNT, 10.0)
        }
        if (order.items.size > 5) {
            order.applyDiscount(Order.PERCENT_DISCOUNT, 0.2)
        }
    }
}



