/*
 * 1. Instantiate and initialize a Reader to read from a CSV file.
 * 2. Read each line and break it up into tokens.
 * 3. Unmarshal the tokens from each line into an entity, either a Product or a Customer.
 * 4. Add each entity into a Set.
 * 5. Return the Set.
 */
package dry04.form.template.method.work

import dry04.form.template.method.Product
import java.io.BufferedReader
import java.math.BigDecimal

@Suppress("UNCHECKED_CAST")
class ProductCSVReader : CsvReader<Product>() {
    override fun <T> createProduct(tokens: Array<String>): T =
        Product(tokens[0].toInt(), tokens[1], BigDecimal(tokens[2])) as T
}

