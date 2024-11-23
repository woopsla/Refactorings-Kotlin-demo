/*
 * SMELL: Unbalanced Branch
 *   -- You have a group of nested conditionals and it is 
 *   	hard to determine the normal flow of code execution.
 * 
 * TREATMENT: Replace Nested Conditional with Guard Clauses
 *   -- Isolate all "special checks and edge cases" into separate
 *   	clauses and place them before the main checks.
 *
 *   -- Ideally, you should have a "flat" list of conditionals,
 *   	one after the other.
 *
 *   -- Forget about "Single Exit Point"
 */
package scp07.replace.nested.conditional.with.guard.clauses.work

class Payout {
    private var isDead = false
    private var isSeparated = false
    private var isRetired = false

    fun payAmount(): Double {
        // ...
        return if (isDead) {
            deadAmount()
        } else {
            if (isSeparated) {
                separatedAmount()
            } else {
                if (isRetired) {
                    retiredAmount()
                } else {
                    normalPayAmount()
                }
            }
        }
    }

    private fun normalPayAmount(): Double = TODO()
    private fun retiredAmount(): Double = TODO()
    private fun separatedAmount(): Double = TODO()
    private fun deadAmount(): Double = TODO()
}
