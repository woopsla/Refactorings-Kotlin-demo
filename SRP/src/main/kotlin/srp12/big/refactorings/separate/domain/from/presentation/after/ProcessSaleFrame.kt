package srp12.big.refactorings.separate.domain.from.presentation.after

import srp12.big.refactorings.separate.domain.from.presentation.SpringUtilities
import java.awt.BorderLayout
import java.awt.EventQueue
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
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

    private var sale: Sale? = null

    fun doIt() {
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
        contentPane = JPanel().apply {
            border = EmptyBorder(5, 5, 5, 5)
            layout = BorderLayout()
            add("North", buildNorthPanel())
            add("Center", buildCenterPanel())
            add("South", buildSouthPanel())
            // Crude way to adjust spacing :-(
            add("West", JLabel("        "))
            add("East", JLabel("        "))
        }
        setContentPane(contentPane)
    }

    private fun buildSouthPanel(): JPanel {
        newSaleButton.addActionListener(this)
        enterItemButton.addActionListener(this)
        endSaleButton.addActionListener(this)

        return JPanel().apply {
            add(newSaleButton)
            add(enterItemButton.apply {
                isEnabled = false
            })
            add(endSaleButton.apply {
                isEnabled = false
            })
        }
    }

    private fun buildCenterPanel(): JPanel {
        val panel = JPanel(SpringLayout()).apply {
            add(JLabel("ItemId", JLabel.TRAILING))
            add(itemId)

            add(JLabel("Quantity", JLabel.TRAILING))
            add(quantity)

            add(JLabel("Description", JLabel.TRAILING))
            add(descriptionField)

            add(JLabel("Price", JLabel.TRAILING))
            add(priceField)

            add(JLabel("SubTotal", JLabel.TRAILING))
            add(subTotalField)

            add(JLabel("Total", JLabel.TRAILING))
            add(totalField)
        }

        // Layout the panel.
        SpringUtilities.makeCompactGrid(
            panel, 6, 2,  // rows, cols
            9, 6,  // initX, initY
            9, 6
        ) // xPad, yPad
        return panel
    }

    private fun buildNorthPanel(): JPanel =
        JPanel().apply {
            add(JLabel("Welcome to Fantastic Market!").apply {
                font = Font("Serif", Font.PLAIN, 20)
            })
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
            when (action) {
                "New Sale" -> makeNewSale()
                "End Sale" -> endSale()
                "Enter Item" -> enterItem()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun enterItem() {
        val editor = quantity.editor
        val qty = (editor as DefaultEditor).textField.text.toInt()
        val id = itemId.selectedItem as String

        sale?.makeNewLineItem(id, qty)

        descriptionField.text = sale?.getDescription(id)
        priceField.text = sale?.getPrice(id).toString()
        subTotalField.text = sale?.getSubTotal(id).toString()
        totalField.text = sale?.total.toString()
    }

    private fun endSale() {
        enterItemButton.isEnabled = false
        endSaleButton.isEnabled = false

        sale?.endSale()
    }

    private fun makeNewSale() {
        resetGUI()
        enterItemButton.isEnabled = true
        endSaleButton.isEnabled = true

        sale = Sale(1, LocalDate.now()) // dummy id for now
    }
}
