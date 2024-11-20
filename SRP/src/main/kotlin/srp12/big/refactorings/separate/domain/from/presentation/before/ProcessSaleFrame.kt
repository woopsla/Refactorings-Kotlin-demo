package srp12.big.refactorings.separate.domain.from.presentation.before

import org.h2.tools.DeleteDbFiles
import srp12.big.refactorings.separate.domain.from.presentation.SpringUtilities
import java.awt.BorderLayout
import java.awt.EventQueue
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.sql.*
import java.time.LocalDate
import javax.swing.*
import javax.swing.JSpinner.DefaultEditor
import javax.swing.border.EmptyBorder

class ProcessSaleFrame : JFrame(), ActionListener {
    private val itemIds = arrayOf("012569-798380", "786936-803488", "043396-150232")

    private val contentPane: JPanel
    private val itemId = JComboBox(itemIds)
    private val quantity = JSpinner(SpinnerNumberModel(1, 0, 100, 1))
    private val descriptionField = JTextField(10)
    private val priceField = JTextField(10)
    private val subTotalField = JTextField(10)
    private val totalField = JTextField(10)

    private val newSaleButton = JButton("New Sale")
    private val enterItemButton = JButton("Enter Item")
    private val endSaleButton = JButton("End Sale")

    private var total = 0.0

    private var connection: Connection? = null

    private var stmt: Statement? = null

    fun doIt() {
        createDB()

        EventQueue.invokeLater {
            try {
                isVisible = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Create the frame.
     */
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setBounds(100, 100, 500, 500)
        contentPane = JPanel()
        contentPane.border = EmptyBorder(5, 5, 5, 5)
        contentPane.layout = BorderLayout()
        setContentPane(contentPane)

        contentPane.add("North", buildNorthPanel())
        contentPane.add("Center", buildCenterPanel())
        contentPane.add("South", buildSouthPanel())
        // Crude way to adjust spacing :-(
        contentPane.add("West", JLabel("        "))
        contentPane.add("East", JLabel("        "))
    }

    private fun buildSouthPanel(): JPanel {
        val panel = JPanel()

        newSaleButton.addActionListener(this)
        panel.add(newSaleButton)

        enterItemButton.addActionListener(this)
        enterItemButton.isEnabled = false
        panel.add(enterItemButton)

        endSaleButton.addActionListener(this)
        endSaleButton.isEnabled = false
        panel.add(endSaleButton)

        return panel
    }

    private fun buildCenterPanel(): JPanel {
        val panel = JPanel(SpringLayout())

        panel.add(JLabel("ItemId", JLabel.TRAILING))
        panel.add(itemId)

        panel.add(JLabel("Quantity", JLabel.TRAILING))
        panel.add(quantity)

        panel.add(JLabel("Description", JLabel.TRAILING))
        panel.add(descriptionField)

        panel.add(JLabel("Price", JLabel.TRAILING))
        panel.add(priceField)

        panel.add(JLabel("SubTotal", JLabel.TRAILING))
        panel.add(subTotalField)

        panel.add(JLabel("Total", JLabel.TRAILING))
        panel.add(totalField)

        // Layout the panel.
        SpringUtilities.makeCompactGrid(
            panel, 6, 2,  // rows, cols
            9, 6,  // initX, initY
            9, 6
        ) // xPad, yPad
        return panel
    }

    private fun buildNorthPanel(): JPanel {
        val panel = JPanel()
        val label = JLabel("Welcome to Fantastic Market!")
        label.font = Font("Serif", Font.PLAIN, 20)
        panel.add(label)
        return panel
    }

    private fun resetGUI() {
        itemId.selectedIndex = 0
        quantity.model = SpinnerNumberModel(1, 0, 100, 1)
        descriptionField.text = ""
        priceField.text = ""
        subTotalField.text = ""
        totalField.text = ""
    }

    override fun actionPerformed(event: ActionEvent) {
        val action = event.actionCommand
        try {
            if (action == "New Sale") {
                makeNewSale()
            } else if (action == "End Sale") {
                endSale()
            } else if (action == "Enter Item") {
                enterItem()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun enterItem() {
        val editor = quantity.editor
        val qty = (editor as DefaultEditor).textField.text.toInt()
        val id = itemId.selectedItem as String

        try {
            prepareTransaction()

            stmt?.execute(
                "INSERT INTO LINEITEM (ItemId, Quantity, ProductDescriptionId, SaleId) VALUES('"
                        + id + "', " + qty + ","
                        + "(SELECT ItemId from PRODUCT_DESCRIPTION WHERE ItemId = '" + id +
                        "'),"
                        + "(SELECT SaleId from SALE WHERE SaleId = 1))"
            )

            val rs = stmt?.executeQuery(
                "select * from PRODUCT_DESCRIPTION where ItemId = '$id'"
            )
            var price = 0
            var desc: String? = ""
            if (rs != null) {
                while (rs.next()) {
                    price = rs.getInt("Price")
                    desc = rs.getString("Description")
                }
            }

            val subTotal = (price * qty).toDouble()
            total += subTotal

            descriptionField.text = desc
            priceField.text = price.toString()
            subTotalField.text = subTotal.toString()
            totalField.text = total.toString()

            wrapUptransaction()
        } catch (e: SQLException) {
            println("Exception Message " + e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun endSale() {
        enterItemButton.isEnabled = false
        endSaleButton.isEnabled = false

        try {
            prepareTransaction()

            stmt?.executeUpdate("UPDATE SALE set TOTAL = $total WHERE SaleId in (1)")

            wrapUptransaction()
        } catch (e: SQLException) {
            println("Exception Message " + e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun makeNewSale() {
        resetGUI()
        enterItemButton.isEnabled = true
        endSaleButton.isEnabled = true

        try {
            prepareTransaction()

            stmt!!.execute("INSERT INTO SALE (OccurredOn) VALUES('" + Date.valueOf(LocalDate.now()) + "')")

            wrapUptransaction()
        } catch (e: SQLException) {
            println("Exception Message " + e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createDB() {
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
    private fun wrapUptransaction() {
        stmt?.close()
        connection?.let {
            it.commit()
            it.close()
        }
    }

    @Throws(SQLException::class)
    private fun prepareTransaction() {
        stmt = null
        connection = dBConnection()?.also {
            it.autoCommit = false
            stmt = it.createStatement()
        }
    }

    @Throws(SQLException::class)
    private fun createLineItemTable() {
        stmt?.execute(
            "CREATE TABLE LINEITEM "
                    + "(ItemId varchar(13) not null,"
                    + "Quantity int,"
                    + "ProductDescriptionId varchar(13),"
                    + "SaleId int,"
                    + "primary key (ItemId),"
                    + "foreign key (ProductDescriptionId) references PRODUCT_DESCRIPTION,"
                    + "foreign key (SaleId) references SALE)"
        )
    }

    @Throws(SQLException::class)
    private fun createSaleTable() {
        stmt?.execute(
            "CREATE TABLE SALE "
                    + "(SaleId int not null auto_increment,"
                    + "OccurredOn Date,"
                    + "Total double,"
                    + "primary key(SaleId))"
        )
    }

    @Throws(SQLException::class)
    private fun createProductDescriptionTable() {
        stmt?.execute(
            "CREATE TABLE PRODUCT_DESCRIPTION "
                    + "(ItemId varchar(13) not null,"
                    + "Description varchar(80),"
                    + "Price int,"
                    + "primary key(ItemId))"
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

    private fun dBConnection(): Connection? {
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

    companion object {
        private const val DB_DRIVER = "org.h2.Driver"
        private const val DB_CONNECTION = "jdbc:h2:~/h2db/pos"
        private const val DB_USER = "sa"
        private const val DB_PASSWORD = ""
    }
}