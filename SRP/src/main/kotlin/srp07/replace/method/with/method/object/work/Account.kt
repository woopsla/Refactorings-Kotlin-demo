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
    fun gamma(inputVal: Int, quantity: Int, yearToDate: Int): Int =
        Gamma(this, inputVal, quantity, yearToDate).compute()


    // ...
    private fun delta() = 7

    inner class Gamma(
        private val account: Account,
        private val inputVal: Int,
        private val quantity: Int,
        private val yearToDate: Int
    ) {
        private var importantValue1: Int = 0
        private var importantValue2: Int = 0
        private var importantValue3: Int = 0

        fun compute(): Int {
            important1()
            important2()
            important3()
            // and so on…
            return importantValue3 - 2 * importantValue1
        }

        private fun important3() {
            importantValue3 = importantValue2 * 7
        }

        private fun important2() {
            importantValue2 = (inputVal * yearToDate) + 100
            if ((yearToDate - importantValue1) > 100) { // Important thing
                importantValue2 -= 20
            }
        }

        private fun important1() {
            importantValue1 = (inputVal * quantity) + delta()
        }
    }
}

