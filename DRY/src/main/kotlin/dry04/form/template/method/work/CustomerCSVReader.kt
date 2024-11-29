/*
 * 1. Instantiate and initialize a Reader to read from a CSV file.
 * 2. Read each line and break it up into tokens.
 * 3. Unmarshal the tokens from each line into an entity, either a Product or a Customer.
 * 4. Add each entity into a Set.
 * 5. Return the Set.
 */
package dry04.form.template.method.work

import dry04.form.template.method.Customer
import java.io.BufferedReader

@Suppress("UNCHECKED_CAST")
class CustomerCSVReader : CsvReader<Customer>() {
    override fun <T> createProduct(tokens: Array<String>): T =
        Customer(tokens[0], tokens[1].toInt(), tokens[2], tokens[3]) as T
}
