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
package srp11.replace.datavalue.with.`object`.work

class Order(val customer: Customer) {
    // ...
}

class Customer(val name: String) {
    // ...
}

// Client code, which uses Order class.
private fun numberOfOrdersFor(orders: List<Order>, customer: String): Int =
    orders.count { it.customer.name == customer }