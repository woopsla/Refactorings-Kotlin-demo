/*
 * SMELL: Feature Envy
 *   -- A method is used more in another class than in its own class.
 * 
 * TREATMENT: Move Method
 *   -- Create a new method in the class that uses the method the most,   
 *      then move code from the old method to there. 
 *   -- Turn the code of the original method into a reference to the 
 *   	new method in the other class or else remove it entirely.
 */
package srp08.move.method.before

// ...
/* Say, there will be several new account types, and each account type
 * will have different rules for how to calculate overdraft fees,
 * when the bank's customer attempts to spend more money than is available.
 */
class Account(private val type: AccountType) {
    private var daysOverdrawn = 0

    private fun overdraftCharge(): Double =
        if (type.isPremium) {
            if (daysOverdrawn > 7)
                10.0 + (daysOverdrawn - 7) * 0.85
            else 10.0
        } else {
            daysOverdrawn * 1.75
        }

    fun bankCharge(): Double =
        if (daysOverdrawn > 0) {
            overdraftCharge() + 4.5
        } else {
            4.5
        }
}

class AccountType(val isPremium: Boolean = false)
