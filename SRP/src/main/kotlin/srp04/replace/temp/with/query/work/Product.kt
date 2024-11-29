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
package srp04.replace.temp.with.query.work

// DRY violation ==> WET (We Enjoy Typing, Waste Everyone's Time)
class Product {
    private var quantity: Int = 3
    private var itemPrice: Double = 0.0

    fun price(): Double {
        val discountFactor = if (getBasePrice() > 1000) 0.95 else 0.98;
        return getBasePrice() * discountFactor
    }

    fun priceForVIP(): Double = getBasePrice() * 0.7

    private fun getBasePrice(): Double = quantity * itemPrice
}
