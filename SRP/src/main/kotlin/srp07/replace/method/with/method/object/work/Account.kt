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
        return Gamma(inputVal, quantity, yearToDate).apply()
    }

    inner class Gamma(val inputVal: Int, val quantity: Int, val yearToDate: Int) {
        var importantValue1: Int = 0
        var importantValue2: Int = 0
        var importantValue3: Int = 0

        fun apply(): Int {
            getValue1()
            getValue2()
            getValue3()
            // and so on…
            return importantValue3 - 2 * importantValue1
        }

        private fun getValue3() {
            if ((yearToDate - importantValue1) > 100) { // Important thing
                importantValue2 -= 20
            }
            importantValue3 = importantValue2 * 7
        }

        private fun getValue2() {
            importantValue2 = (inputVal * yearToDate) + 100
        }

        private fun getValue1() {
            importantValue1 = (inputVal * quantity) + delta()
        }
    }

    // ...
    private fun delta() = 7
}
