package srp14.move.embellishment.to.decorator.before

/**
 * What if we want to add more features to the writer, like capitalization?
 */
class StringWriterOrig() : Writer {
    private val target = StringBuilder()

    override fun write(msg: String) {
        target.append(msg)
    }

    override fun toString(): String = target.toString()
}
