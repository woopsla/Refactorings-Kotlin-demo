/*
 * SMELL: Poor Cohesion
 *   -- When one class does the work of two, awkwardness results.
 * 
 * TREATMENT: Extract Class
 *   -- Create a new class and place the fields and methods responsible 
 *      for the relevant functionality in it.
 */
package srp09.extractclass.work

class Person(
    val name: String,
    var age: Int,
    private var contactInfo: ContactInfo?,
) {
    // ...
    val phone: Phone? = contactInfo?.phone
    val address: Address? = contactInfo?.address
    val email: EmailAddress? = contactInfo?.emailAddress
}

@JvmInline
value class Phone(val number: String)

@JvmInline
value class EmailAddress(val email: String)
data class Address(val street: String, val city: String, val zip: String)

class ContactInfo(
    var phone: Phone?,
    var address: Address?,
    var emailAddress: EmailAddress?,
)
