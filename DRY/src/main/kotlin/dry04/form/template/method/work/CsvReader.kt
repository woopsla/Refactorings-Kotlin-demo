package dry04.form.template.method.work

import java.io.BufferedReader
import kotlin.text.split
import kotlin.text.trim

abstract class CsvReader<T> {
    fun getAll(bufferedReader: BufferedReader): Set<T> {
        val returnSet = mutableSetOf<T>()
        bufferedReader.use { reader ->
            var line = reader.readLine()
            while (line != null && line.trim { it <= ' ' } != "") {
                val tokens = line.split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val item: T = createProduct(tokens)
                returnSet.add(item)
                line = reader.readLine()
            }
        }
        return returnSet
    }

    abstract fun <T> createProduct(tokens: Array<String>): T
}