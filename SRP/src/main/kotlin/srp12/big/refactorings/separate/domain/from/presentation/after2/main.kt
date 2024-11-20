package org.scarlet.srp12.big.refactorings.separate.domain.from.presentation.after2

fun main() {
    DB.createDB()
    val saleService = SaleService(DB())

    val frame = ProcessSaleFrame(saleService)
    frame.doIt()
}
