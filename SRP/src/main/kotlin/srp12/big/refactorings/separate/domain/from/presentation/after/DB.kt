package srp12.big.refactorings.separate.domain.from.presentation.after

import org.h2.tools.DeleteDbFiles
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

object DB {
    private const val DB_DRIVER = "org.h2.Driver"
    private const val DB_CONNECTION = "jdbc:h2:~/h2db/pos"
    private const val DB_USER = "sa"
    private const val DB_PASSWORD = ""

    var connection: Connection? = null
    var stmt: Statement? = null

    val dBConnection: Connection?
        get() {
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
        connection = dBConnection?.also {
            it.autoCommit = false
            stmt = it.createStatement()
        }
    }

    @Throws(SQLException::class)
    fun createLineItemTable() {
        stmt?.execute(
            "CREATE TABLE LINEITEM "
                    + "(ItemID varchar(13) not null,"
                    + "Quantity int,"
                    + "ProductDescriptionID varchar(13),"
                    + "SaleID int,"
                    + "primary key (ItemID),"
                    + "foreign key (ProductDescriptionID) references PRODUCT_DESCRIPTION,"
                    + "foreign key (SaleID) references SALE)"
        )
    }

    @Throws(SQLException::class)
    private fun createSaleTable() {
        stmt?.execute(
            "CREATE TABLE SALE "
                    + "(SaleID int not null auto_increment,"
                    + "OccurredOn Date,"
                    + "Total double,"
                    + "primary key(SaleId))"
        )
    }

    @Throws(SQLException::class)
    private fun createProductDescriptionTable() {
        stmt?.execute(
            "CREATE TABLE PRODUCT_DESCRIPTION "
                    + "(ItemID varchar(13) not null,"
                    + "Description varchar(80),"
                    + "Price int,"
                    + "primary key(ItemID))"
        )

        stmt?.execute(
            "INSERT INTO PRODUCT_DESCRIPTION(ItemID, Description, Price)"
                    + " VALUES('012569-798380','2001: A Space Odyssey', 30.0)"
        )
        stmt?.execute(
            "INSERT INTO PRODUCT_DESCRIPTION(ItemID, Description, Price)"
                    + " VALUES('786936-803488','Alice in Wonderland', 50.0)"
        )
        stmt?.execute(
            "INSERT INTO PRODUCT_DESCRIPTION(ItemID, Description, Price)"
                    + " VALUES('043396-150232','Black Hawk Down', 25.0)"
        )
    }
}
