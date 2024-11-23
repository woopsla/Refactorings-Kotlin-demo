/*
 * SMELL: Long Method
 *   -- You have a long method in which the local variables 
 *      are so intertwined that you cannot apply "Extract Method".
 * 
 * TREATMENT: Replace Method with Method Object
 *   -- Turn the method into its own object so that all the local 
 *      variables become fields on that object. 
 */
package srp07.replace.method.with.method.`object`.work

class Account {
    // ... Select function body in {} to convert to method object
    fun gamma(inputVal: Int, quantity: Int, yearToDate: Int): Int {
        val importantValue1 = (inputVal * quantity) + delta()
        var importantValue2 = (inputVal * yearToDate) + 100
        if ((yearToDate - importantValue1) > 100) { // Important thing
            importantValue2 -= 20
        }
        val importantValue3 = importantValue2 * 7
        // and so on…
        return importantValue3 - 2 * importantValue1
    }

    // ...
    private fun delta() = 7
}
