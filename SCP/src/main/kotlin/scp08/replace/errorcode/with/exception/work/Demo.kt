/*
 * SMELL: Error Code
 *   -- A method returns a special value that indicates an error.
 *      (Unix tradition)
 * 
 * TREATMENT: Replace Error Code with Exception
 *   -- Throw an exception instead.
 *   -- Exceptions clearly separate normal processing from error processing 
 *      which makes programs easier to understand.
 */
package scp08.replace.errorcode.with.exception.work

class Demo {
    private var balance = 0

    fun withdraw(amount: Int): Int {
        if (amount > balance) {
            return -1
        } else {
            balance -= amount
            return 0
        }
    }
}
