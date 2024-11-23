/*
 * 1. Instantiate and initialize a Reader to read from a CSV file.
 * 2. Read each line and break it up into tokens.
 * 3. Unmarshal the tokens from each line into an entity, either a Product or a Customer.
 * 4. Add each entity into a Set.
 * 5. Return the Set.
 */
package dry04.form.template.method.before

import dry04.form.template.method.Customer
import java.io.BufferedReader

class CustomerCSVReader {
    fun getAll(bufferedReader: BufferedReader): Set<Customer> {
        val returnSet = mutableSetOf<Customer>()
        bufferedReader.use { reader ->
            var line = reader.readLine()
            while (line != null && line.trim { it <= ' ' } != "") {
                val tokens = line.split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val customer: Customer = Customer(tokens[0], tokens[1].toInt(), tokens[2], tokens[3])
                returnSet.add(customer)
                line = reader.readLine()
            }
        }
        return returnSet
    }
}
