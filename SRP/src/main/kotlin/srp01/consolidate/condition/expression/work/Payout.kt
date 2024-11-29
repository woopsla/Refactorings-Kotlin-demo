/*
 * SMELL: A Sequence of Condition Tests With The Same Result
 *   -- You have a sequence of conditional tests with the same result.
 *
 * TREATMENT: Consolidate Conditional Expression
 *   -- Combine them into a single conditional expression.
 *   -- It makes the check clearer.
 *   -- May continue refactoring with "Extract Method".
 */
package srp01.consolidate.condition.expression.work

class Payout {
    // ...
    var seniority: Int = 0
    var monthsDisabled: Int = 0
    var isPartTime: Boolean = false

    fun disabilityAmount(): Double {
        if (!isEligibleForDisability()) {    // not eligible for disability
            return 0.0
        }

        // compute the disability amount
        // ...
        return 1234.0 // dummy
    }

    private fun isEligibleForDisability(): Boolean =
        seniority >= 2 && monthsDisabled <= 12 && !isPartTime
}