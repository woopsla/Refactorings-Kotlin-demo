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
    var age: Int,
    var phone: Phone?,
    var address: Address?,
    var emailAddress: EmailAddress?,
)

// Wrapper type, Tiny type
@JvmInline
value class Phone(val number: String)

@JvmInline
value class EmailAddress(val email: String)
data class Address(val street: String, val city: String, val zip: String)
