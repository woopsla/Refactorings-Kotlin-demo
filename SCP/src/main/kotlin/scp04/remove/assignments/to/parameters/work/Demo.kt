/*
 * SMELL: Non Const Parameters
 *   -- Some value is assigned to a parameter inside method's body.
 *   -- Same problem as DD-anomaly.
 *
 * TREATMENT: Remove Assignment To Parameters
 *   -- Use a local variable(s) instead of a parameter.
 *   -- You can enforce this convention with the final/const keyword
 */
package scp04.remove.assignments.to.parameters.work

fun getDiscount(inputVal: Int, quantity: Int, yearToDate: Int): Int {
    var discount = inputVal

    if (inputVal > 50) {
        discount -= 2
    }
    if (quantity > 100) {
        discount -= 1
    }
    if (yearToDate > 10000) {
        discount -= 4
    }
    return discount
}
