package srp12.big.refactorings.separate.domain.from.presentation.after2

import java.time.LocalDate

class Sale(private val saleID: Int, val occurredOn: LocalDate) {
    var total: Double = 0.0
}
