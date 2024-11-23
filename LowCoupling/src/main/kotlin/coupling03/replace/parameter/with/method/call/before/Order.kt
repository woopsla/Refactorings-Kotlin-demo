/*
 * SMELL: Long Parameter List
 *   -- Before a method call, a second method is run and its 
 *      result is sent back to the first method as an argument. 
 *      But the parameter value could have been obtained inside 
 *      the method being called.
 * 
 * TREATMENT: Replace Parameter with Method Call
 *   -- Instead of passing the value through a parameter, place 
 *      the value-getting code inside the method.
 */
package coupling03.replace.parameter.with.method.call.before

class Order(val quantity: Int, val itemPrice: Int) {
    fun price(): Double {
        // ...
        val basePrice = getBasePrice()  // Get parameter value from method call
        val discountLevel = getDiscountLevel() // Get parameter value from method call
        return discountedPrice(basePrice, discountLevel) // Pass the values as parameters
    }

    private fun getDiscountLevel() = if (quantity > 100) 2 else 1

    private fun getBasePrice(): Int = quantity * itemPrice

    // The method for getting the discount (discountedPrice) is
    // currently nearly impossible to use separately from the method
    // for getting the price (price), since you must get the
    // values of all parameters prior to it.
    private fun discountedPrice(basePrice: Int, discountLevel: Int): Double =
        if (discountLevel == 2) {
            basePrice * 0.1
        } else {
            basePrice * 0.05
        }
}
