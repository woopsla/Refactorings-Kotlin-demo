package srp12.big.refactorings.separate.domain.from.presentation.after2

import org.h2.tools.DeleteDbFiles
import java.sql.*

class DB : SaleRepository {
    override fun save(sale: Sale) {
        val now = sale.occurredOn
        try {
            prepareTransaction()

            stmt?.execute("INSERT INTO SALE (OccurredOn) VALUES('$now')")

            wrapUptransaction()
        } catch (e: SQLException) {
            println("Exception Message " + e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun update(sale: Sale) {
        try {
            prepareTransaction()

            stmt?.executeUpdate("UPDATE SALE set TOTAL = " + sale.total + " WHERE SaleId in (1)")

            wrapUptransaction()
        } catch (e: SQLException) {
            println("Exception Message " + e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun save(item: LineItem) {
        try {
            prepareTransaction()

            stmt?.execute(
                "INSERT INTO LINEITEM (ItemId, Quantity, ProductDescriptionId, SaleId) VALUES('" + item.itemId + "', "
                        + item.quantity + "," + "(SELECT ItemId from PRODUCT_DESCRIPTION WHERE ItemId = '" + item.itemId + "'),"
                        + "(SELECT SaleId from SALE WHERE SaleId = 1))"
            )

            wrapUptransaction()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override fun findById(id: String): ProductDescription? {
        var price = 0.0
        var desc: String? = null

        try {
            prepareTransaction()

            val rs = stmt?.executeQuery(
                "select * from PRODUCT_DESCRIPTION where ItemId = '$id'"
            )?.let {
                while (it.next()) {
                    price = it.getInt("Price").toDouble()
                    desc = it.getString("Description")
                }
            }

            wrapUptransaction()
        } catch (e: SQLException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return ProductDescription(id, price, desc ?: "")
    }

    companion object {
        private const val DB_DRIVER = "org.h2.Driver"
        private const val DB_CONNECTION = "jdbc:h2:~/h2db/pos"
        private const val DB_USER = "sa"
        private const val DB_PASSWORD = ""

        var connection: Connection? = null
        var stmt: Statement? = null

        fun getDBConnection(): Connection? {
            connection = null
            try {
                Class.forName(DB_DRIVER)
                connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)
            } catch (e: ClassNotFoundException) {
                println(e.message)
                e.printStackTrace()
            } catch (e: SQLException) {
                println(e.message)
                e.printStackTrace()
            }
            return connection
        }

        fun createDB() {
            DeleteDbFiles.execute("~/h2db", "pos", true)

            try {
                prepareTransaction()

                createProductDescriptionTable()
                createSaleTable()
                createLineItemTable()

                wrapUptransaction()
            } catch (e: SQLException) {
                println("Exception Message " + e.localizedMessage)
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @Throws(SQLException::class)
        fun wrapUptransaction() {
            stmt?.close()
            connection?.commit()
            connection?.close()
        }

        @Throws(SQLException::class)
        fun prepareTransaction() {
            stmt = null
            connection = getDBConnection()?.also {
                it.autoCommit = false
                stmt = it.createStatement()
            }
        }

        @Throws(SQLException::class)
        fun createLineItemTable() {
            stmt?.execute(
                "CREATE TABLE LINEITEM " + "(ItemId varchar(13) not null," + "Quantity int,"
                        + "ProductDescriptionId varchar(13)," + "SaleId int," + "primary key (ItemId),"
                        + "foreign key (ProductDescriptionId) references PRODUCT_DESCRIPTION,"
                        + "foreign key (SaleId) references SALE)"
            )
        }

        @Throws(SQLException::class)
        fun createSaleTable() {
            stmt?.execute(
                "CREATE TABLE SALE " + "(SaleId int not null auto_increment," + "OccurredOn Date,"
                        + "Total double," + "primary key(SaleId))"
            )
        }

        @Throws(SQLException::class)
        fun createProductDescriptionTable() {
            stmt?.execute(
                "CREATE TABLE PRODUCT_DESCRIPTION " + "(ItemId varchar(13) not null," + "Description varchar(80),"
                        + "Price int," + "primary key(ItemId))"
            )

            stmt?.execute(
                "INSERT INTO PRODUCT_DESCRIPTION(ItemId, Description, Price)"
                        + " VALUES('012569-798380','2001: A Space Odyssey', 30.0)"
            )
            stmt?.execute(
                "INSERT INTO PRODUCT_DESCRIPTION(ItemId, Description, Price)"
                        + " VALUES('786936-803488','Alice in Wonderland', 50.0)"
            )
            stmt?.execute(
                "INSERT INTO PRODUCT_DESCRIPTION(ItemId, Description, Price)"
                        + " VALUES('043396-150232','Black Hawk Down', 25.0)"
            )
        }
    }
}
