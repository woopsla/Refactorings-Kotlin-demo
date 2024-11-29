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

import kotlin.system.exitProcess

object BalanceException : Exception("Withdraw amount exceed balance") {
    private fun readResolve(): Any = BalanceException
}

class Demo {
    var balance = 50

    fun withdraw(amount: Int): Int {
        if (amount > balance) {
            throw BalanceException
        } else {
            balance -= amount
            return 0
        }
    }
}

fun main() {
    val demo = Demo()
    try {
        if (demo.withdraw(100) == -1) {
            println("Withdraw amount exceed balance")
            exitProcess(1)
        }
    } catch (e: BalanceException) {
        println(e.message)
        exitProcess(1)
    }
    println("Balance: ${demo.balance}")
}