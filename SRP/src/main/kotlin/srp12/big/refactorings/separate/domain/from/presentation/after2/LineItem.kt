package srp12.big.refactorings.separate.domain.from.presentation.after2

data class LineItem(val itemId: String, val quantity: Int, val description: ProductDescription) {
    val subTotal: Double = quantity * description.price
}
