package srp14.move.embellishment.to.decorator.work

fun main() {
    var writer: Writer = StringWriter(isCapitalized = false)
    writer.write("This is stupid")
    println(writer)

    writer = StringWriter(isCapitalized = true)
    writer.write("This is stupid")
    println(writer)
}
