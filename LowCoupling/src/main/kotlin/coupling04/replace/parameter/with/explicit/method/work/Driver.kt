/*
 * SMELL: Control Couple
 *   -- A method is split into parts, each of which is run 
 *      depending on the value of a parameter.
 * 
 * TREATMENT: Replace Parameter with Explicit Method
 *   -- Extract the individual parts of the method into their 
 *      own methods and call them instead of the original method.
 */
package coupling04.replace.parameter.with.explicit.method.work

class Item

class Order {
    var price = 0.0
    val items = mutableListOf<Item>()

    fun applyFixedDiscount(discountAmount: Double) {
        price -= discountAmount
    }

    fun applyPercentDiscount(percent: Double) {
        price -= price * percent
    }
}

object Driver {
    private var weekend = false

    @JvmStatic
    fun main(args: Array<String>) {
        // Somewhere in client code
        val order = Order()
        if (weekend) {
            order.applyFixedDiscount(10.0)
        }
        if (order.items.size > 5) {
            order.applyPercentDiscount(0.2)
        }
    }
}



