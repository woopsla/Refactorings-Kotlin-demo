package srp12.big.refactorings.separate.domain.from.presentation.after

import java.sql.Date
import java.sql.SQLException
import java.time.LocalDate

class Sale(
    private val saleId: Int,
    private val occurredOn: LocalDate,
) {
    @JvmField
    var total: Double = 0.0
    private val items = mutableListOf<LineItem>()
    private var currentItem: LineItem? = null

    init {
        save()
    }

    fun makeNewLineItem(itemId: String, quantity: Int) {
        try {
            DB.prepareTransaction()

            val rs = DB.stmt?.executeQuery(
                "select * from PRODUCT_DESCRIPTION where ItemId = '$itemId'"
            )

            var price = 0
            var desc: String = ""
            if (rs != null) {
                while (rs.next()) {
                    price = rs.getInt("Price")
                    desc = rs.getString("Description")
                }
            }
            DB.wrapUptransaction()

            val description = ProductDescription(itemId, price.toDouble(), desc)

            currentItem = LineItem(itemId, quantity, description).also {
                total += it.subTotal
                items.add(it)
                it.save()
            }

        } catch (e: SQLException) {
            println("Exception Message " + e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun save() {
        try {
            DB.prepareTransaction()

            DB.stmt?.execute("INSERT INTO SALE (OccurredOn) VALUES('" + Date.valueOf(occurredOn) + "')")

            DB.wrapUptransaction()
        } catch (e: SQLException) {
            println("Exception Message " + e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun endSale() {
        try {
            DB.prepareTransaction()

            DB.stmt?.executeUpdate("UPDATE SALE set TOTAL = $total WHERE SaleId in (1)")

            DB.wrapUptransaction()
        } catch (e: SQLException) {
            println("Exception Message " + e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getSubTotal(id: String): Double {
        return currentItem?.subTotal ?: 0.0
    }

    fun getPrice(id: String): Double {
        return currentItem?.description?.price ?: 0.0
    }

    fun getDescription(id: String): String {
        return currentItem?.description.toString()
    }
}
