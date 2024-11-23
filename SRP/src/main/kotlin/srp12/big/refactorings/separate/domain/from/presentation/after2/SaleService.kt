package srp12.big.refactorings.separate.domain.from.presentation.after2

import java.time.LocalDate

class SaleService(private val saleRepository: SaleRepository) {
    @JvmField
    var total: Double = 0.0

    @JvmField
    var subTotal: Double = 0.0

    @JvmField
    var price: Double = 0.0

    @JvmField
    var description: String = ""

    private var currentSale: Sale? = null
    private var currentLineItem: LineItem? = null

    fun makeNewSale() {
        currentSale = Sale(1, LocalDate.now()) // dummy id for now
            .also { saleRepository.save(it) }
    }

    fun makeNewLineItem(id: String, quantity: Int) {
        saleRepository.findById(id)?.also { description ->
            price = description.price
            currentLineItem = LineItem(id, quantity, description).also {
                subTotal = it.subTotal
                total += subTotal
                saleRepository.save(it)
            }
        }
    }

    fun endSale() {
        currentSale?.let {
            saleRepository.update(it)
        }
    }
}
