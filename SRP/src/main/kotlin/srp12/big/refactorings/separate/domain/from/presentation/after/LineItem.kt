package srp12.big.refactorings.separate.domain.from.presentation.after

import java.sql.SQLException

data class LineItem(val ItemId: String, val quantity: Int, val description: ProductDescription) {
    val subTotal: Double = quantity * description.price

    fun save() {
        try {
            DB.prepareTransaction()

            DB.stmt?.execute(
                "INSERT INTO LINEITEM (ItemId, Quantity, ProductDescriptionId, SaleId) VALUES('"
                        + ItemId + "', " + quantity + ","
                        + "(SELECT ItemId from PRODUCT_DESCRIPTION WHERE ItemId = '" + ItemId +
                        "'),"
                        + "(SELECT SaleId from SALE WHERE SaleId = 1))"
            )
            DB.wrapUptransaction()
        } catch (e: SQLException) {
            println("Exception Message " + e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
