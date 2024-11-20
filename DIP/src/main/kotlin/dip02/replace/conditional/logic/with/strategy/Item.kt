package dip02.replace.conditional.logic.with.strategy

data class Item(private val quantity: Int, private val price: Double) {
    val subTotal: Double = quantity * price
}
