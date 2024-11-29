package refactoring.lab.work

import java.time.LocalDate

class Customer(
    val name: String,
    val dateOfBirth: String,
    val statement: Statement = TextStatement(),
) {
    val rentals = mutableListOf<Rental>()
    val returnedRentals = mutableListOf<Rental>()

    fun rentVideo(video: Video, rentedOn: LocalDate = LocalDate.now()) {
        if (isUnderAgeFor(video)) throw CustomerUnderageException
        rentals.add(Rental(video, rentedOn))
    }

    fun returnVideo(title: String, returnedOn: LocalDate = LocalDate.now()) {
        rentals.find { it.video.title == title }?.also {
            it.returnVideo(returnedOn)
            returnedRentals.add(it)
            rentals.remove(it)
        }
    }

    fun isUnderAgeFor(video: Video): Boolean = !video.canBeCheckedOutFor(dateOfBirth)
    fun statement(): String = TextStatement().statement(this)

    fun getTotalCharge(): Double = returnedRentals.sumOf { it.getCharge() }
    fun getFrequentRentalPoints(): Int = returnedRentals.sumOf { it.getFrequentRentalPoint() }
}

abstract class Statement {
    fun statement(customer: Customer): String {
        val result = StringBuilder()
        writeHeader(customer, result)
        writeBody(customer, result)
        writeFooter(customer, result)
        return result.toString();
    }

    abstract fun writeHeader(customer: Customer, result: StringBuilder)
    abstract fun writeBody(customer: Customer, result: StringBuilder)
    abstract fun writeFooter(customer: Customer, result: StringBuilder)
}

class TextStatement : Statement() {
    override fun writeHeader(customer: Customer, result: StringBuilder) {
        result.append("Rental Record for ${customer.name}\n")
    }

    override fun writeBody(customer: Customer, result: StringBuilder) {
        for (rental in customer.returnedRentals) {
            result.append("\t${rental.video.title}\t${rental.getCharge()}\n");
        }
    }

    override fun writeFooter(customer: Customer, result: StringBuilder) {
        result.append("Amount owed is ${customer.getTotalCharge()}\n");
        result.append("You earned ${customer.getFrequentRentalPoints()} frequent renter points");
    }
}

class HtmlStatement : Statement() {
    override fun writeHeader(customer: Customer, result: StringBuilder) {
        result.append("<H1>Rentals for ${customer.name}</H1>\n")
    }

    override fun writeBody(customer: Customer, result: StringBuilder) {
        for (rental in customer.returnedRentals) {
            result.append("${rental.video.title}: ${rental.getCharge()}<BR>\n");
        }
    }

    override fun writeFooter(customer: Customer, result: StringBuilder) {
        result.append("Amount owed is ${customer.getTotalCharge()}<BR>\n");
        result.append("You earned ${customer.getFrequentRentalPoints()} frequent renter points");
    }
}

