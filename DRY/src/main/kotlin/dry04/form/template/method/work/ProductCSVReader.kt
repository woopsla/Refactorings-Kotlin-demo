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

class ProductCSVReader {
    fun getAll(bufferedReader: BufferedReader): Set<Product> {
        val returnSet = mutableSetOf<Product>()
        bufferedReader.use { reader ->
            var line = reader.readLine()
            while (line != null && line.trim { it <= ' ' } != "") {
                val tokens = line.split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val product: Product = Product(tokens[0].toInt(), tokens[1], BigDecimal(tokens[2]))
                returnSet.add(product)
                line = reader.readLine()
            }
        }
        return returnSet
    }
}
