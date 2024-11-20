/*
 * SMELL: Poor Cohesion
 *   -- When one class does the work of two, awkwardness results.
 * 
 * TREATMENT: Extract Class
 *   -- Create a new class and place the fields and methods responsible 
 *      for the relevant functionality in it.
 */
package srp09.extractclass.before

class Person(
    val name: String,
    val age: Int,
    val phone: Phone,
    val address: Address,
    var mailingAddress: Address?
) {
    // ...
    constructor(
        name: String,
        age: Int,
        phone: Phone,
        address: Address
    ) : this(name, age, phone, address, null)
}

@JvmInline
value class Phone(val number: String)

data class Address(val street: String, val city: String, val zip: String)
