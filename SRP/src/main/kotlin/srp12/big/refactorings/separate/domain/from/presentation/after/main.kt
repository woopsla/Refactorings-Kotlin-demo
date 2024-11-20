package srp12.big.refactorings.separate.domain.from.presentation.after

fun main() {
    DB.createDB()
    val frame = ProcessSaleFrame()
    frame.doIt()
}
