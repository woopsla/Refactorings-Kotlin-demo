package org.scarlet.srp12.big.refactorings.separate.domain.from.presentation.after2

interface SaleRepository {
    fun save(sale: Sale)
    fun update(sale: Sale)
    fun save(item: LineItem)
    fun findById(id: String): ProductDescription?
}
