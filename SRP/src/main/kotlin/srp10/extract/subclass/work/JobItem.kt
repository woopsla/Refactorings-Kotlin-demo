/*
* SMELL: Poor Cohesion
*   -- A class has features that are used only in certain cases.
*
* TREATMENT: Extract Subclass
*   -- Create a subclass for that subset of features.
*/
package srp10.extract.subclass.work

import srp10.extract.subclass.Employee

/*
 * We start with the JobItem class, which tracks the time and
 * materials used to fix a client's car in a local garage.
 * This class is also responsible for calculating the price
 * client should pay.
 */
abstract class JobItem(
    val quantity: Int,
) {
    fun totalPrice(): Int = quantity * unitPrice()

    /*
     * The price usually consists of several items.
     * First, it's the fixed cost of certain parts.
     * Second, it's the cost of a mechanic's time, multiplied
     * by his rate (that can be taken directly from the Employee class).
     *
	 * So, the price is calculated in several ways, all of which
	 * sit in a single class. And that starts to smell as a Large
	 * Class.
	 *
	 * As a solution, we could extract the LaborItem subclass and
	 * move all code, which are associated with manual work, to
	 * that subclass. Then we could leave only fixed amounts in
	 * the original class.
	 */
    abstract fun unitPrice(): Int
}

class LaborItem(
    quantity: Int,
    val employee: Employee?,
) : JobItem(quantity) {
    override fun unitPrice(): Int = employee?.rate ?: 1
}

class PartItem(
    quantity: Int,
    val unitPrice: Int,
) : JobItem(quantity) {
    override fun unitPrice(): Int = unitPrice
}