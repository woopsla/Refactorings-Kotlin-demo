/*
 * SMELL: Long Method or Code Duplicate Across Methods
 *   -- You have a code fragment that can be grouped together.
 *   -- You have the same or similar codes.
 * 
 * TREATMENT: Extract Method
 *   -- Move this code to a separate new method (or function) 
 *      and replace the old code with a call to the method.
 */
package dry01.extract.method.work

class Customer(val name: String) {
    private val orders = mutableListOf<Order>()

    fun printOwing() {
        // Print banner
        println("****************************")
        println("****** Customer Total ******")
        println("****************************")

        // Print details
        println("name: $name")
        println("amount: ${outstanding()}")
    }

    private fun outstanding(): Double {
        var outstanding = 0.0
        for (order in orders) {
            outstanding += order.amount
        }
        return outstanding
    }
}
