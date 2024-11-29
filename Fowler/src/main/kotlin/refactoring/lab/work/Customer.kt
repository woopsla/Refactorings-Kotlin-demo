package refactoring.lab.work

import java.time.LocalDate

class Customer(
    val name: String,
    val dateOfBirth: String,
    private var statement: Statement? = null,
) {
    init {
        setStatement(statement ?: TextStatement(this))
    }

    val rentals = mutableListOf<Rental>()
    val returnedRentals = mutableListOf<Rental>()

    fun rentOf(video: Video, rentedOn: LocalDate = LocalDate.now()) {
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

    fun setStatement(statement: Statement?) {
        this.statement = statement ?: TextStatement(this)
    }

    // SAFETY GUARANTEE: `statement` can never be null
    fun getStatement(): String = statement!!.getStatement()

    fun getTotalCharge(): Double = returnedRentals.sumOf { it.getCharge() }
    fun getFrequentRentalPoints(): Int = returnedRentals.sumOf { it.getFrequentRentalPoint() }
}

abstract class Statement(protected val customer: Customer) {
    fun getStatement(): String {
        val result = StringBuilder()
        writeHeader(result)
        writeBody(result)
        writeFooter(result)
        return result.toString();
    }

    abstract fun writeHeader(result: StringBuilder)
    abstract fun writeBody(result: StringBuilder)
    abstract fun writeFooter(result: StringBuilder)
}

class TextStatement(customer: Customer) : Statement(customer) {
    override fun writeHeader(result: StringBuilder) {
        result.append("Rental Record for ${customer.name}\n")
    }

    override fun writeBody(result: StringBuilder) {
        for (rental in customer.returnedRentals) {
            result.append("\t${rental.video.title}\t${rental.getCharge()}\n");
        }
    }

    override fun writeFooter(result: StringBuilder) {
        result.append("Amount owed is ${customer.getTotalCharge()}\n");
        result.append("You earned ${customer.getFrequentRentalPoints()} frequent renter points");
    }
}

class HtmlStatement(customer: Customer) : Statement(customer) {
    override fun writeHeader(result: StringBuilder) {
        result.append("<H1>Rentals for ${customer.name}</H1>\n")
    }

    override fun writeBody(result: StringBuilder) {
        for (rental in customer.returnedRentals) {
            result.append("${rental.video.title}: ${rental.getCharge()}<BR>\n");
        }
    }

    override fun writeFooter(result: StringBuilder) {
        result.append("Amount owed is ${customer.getTotalCharge()}<BR>\n");
        result.append("You earned ${customer.getFrequentRentalPoints()} frequent renter points");
    }
}

