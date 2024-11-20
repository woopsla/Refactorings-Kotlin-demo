/*
 * SMELL: Duplicate Code, Shotgun Surgery
 *   -- You have a temporary variable to hold the result of expression.
 *   -- Especially, the temporary variable is also defined in other 
 *      methods --> "Shotgun Surgery"
 * 
 * TREATMENT: Replace Temp with Query
 *   -- Extract the expression into a method so that it can be used 
 *      in other methods
 *   -- Lay foundation for later "Extract Method" refactoring.
 */
package srp04.replace.temp.with.query.before

class Product {
    private var quantity: Int = 3
    private var itemPrice: Double = 0.0

    fun price(): Double {
        val basePrice = quantity * itemPrice // <------------
        val discountFactor = if (basePrice > 1000) 0.95 else 0.98;
        return basePrice * discountFactor
    }

    fun priceForVIP(): Double {
        val basePrice = quantity * itemPrice // <-------------
        return basePrice * 0.7
    }
}
