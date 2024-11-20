/*
 * SMELL: Primitive Obsession
 *   -- A class (or group of classes) contains a data field. 
 *      The field has its own behavior and associated data.
 * 
 * TREATMENT: Replace Data Value with Object
 *   -- Create a new class, place the old field and its behavior 
 *      in the class, and store the object of the class in the 
 *      original class.
 *
 */
package srp11.replace.datavalue.with.`object`.before

class Order(val customer: String) {
    // ...
}

// Client code, which uses Order class.
private fun numberOfOrdersFor(orders: List<Order>, customer: String): Int {
    var result = 0

    for (each in orders) {
        if (each.customer == customer) {
            result++
        }
    }
    return result
}