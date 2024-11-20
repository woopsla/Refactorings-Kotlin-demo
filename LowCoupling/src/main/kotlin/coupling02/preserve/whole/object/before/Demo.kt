/*
 * SMELL: Long Parameter List
 *   -- You get several values from an object and then pass them 
 *      as parameters to a method.
 * 
 * TREATMENT: Preserve Whole Object
 *   -- Instead, try passing the whole object.
 *   -- Beware: Avoid stamp coupling 
 *   			(i.e., Using only part of the passed object)
 */
package coupling02.preserve.whole.`object`.before

class Room {
    // ...
    fun withinPlan(plan: HeatingPlan): Boolean {
        val low = lowestTemp()
        val high = highestTemp()
        /*
		 * Currently, we are passing only the temperature 
		 * for analysis but at any time we may need to check 
		 * another room parameter, such as humidity.
		 */
        return plan.withinRange(low, high)
    }

    fun highestTemp(): Int = 100 // dummy
    fun lowestTemp(): Int = 0 // dummy
}

class HeatingPlan(private val range: TempRange) {
    fun withinRange(low: Int, high: Int): Boolean {
        return (low >= range.low && high <= range.high)
    }
}

data class TempRange(val low: Int, val high: Int)
