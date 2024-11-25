package srp15.move.embellishment.to.decorator.before

/**
 * What if we want to add more features to the writer, like capitalization?
 */
class StringWriterOrig() {
    private val target = StringBuilder()

    fun write(msg: String) {
        target.append(msg)
    }

    override fun toString(): String = target.toString()
}
