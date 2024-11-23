package dry04.form.template.method.work

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {
    val csvReader1 = ProductCSVReader()
    val products = csvReader1.getAll(BufferedReader(FileReader(File("products.csv"))))
    products.forEach { println(it) }

    val csvReader2 = CustomerCSVReader()
    val customers = csvReader2.getAll(BufferedReader(FileReader(File("customers.csv"))))
    customers.forEach { println(it) }
}