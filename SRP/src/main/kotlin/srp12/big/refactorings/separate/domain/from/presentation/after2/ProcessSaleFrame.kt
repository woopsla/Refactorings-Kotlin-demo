package org.scarlet.srp12.big.refactorings.separate.domain.from.presentation.after2

import srp12.big.refactorings.separate.domain.from.presentation.SpringUtilities
import java.awt.BorderLayout
import java.awt.EventQueue
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.JSpinner.DefaultEditor
import javax.swing.border.EmptyBorder

class ProcessSaleFrame(private val saleExecutor: SaleService) : JFrame(), ActionListener {
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

    fun doIt() {
        EventQueue.invokeLater {
            try {
                isVisible = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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

        panel.add(JLabel("ItemID", JLabel.TRAILING))
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

        saleExecutor.makeNewLineItem(id, qty)

        descriptionField.text = saleExecutor.description
        priceField.text = saleExecutor.price.toString()
        subTotalField.text = saleExecutor.subTotal.toString()
        totalField.text = saleExecutor.total.toString()
    }

    private fun endSale() {
        enterItemButton.isEnabled = false
        endSaleButton.isEnabled = false

        saleExecutor.endSale()
    }

    private fun makeNewSale() {
        resetGUI()
        enterItemButton.isEnabled = true
        endSaleButton.isEnabled = true

        saleExecutor.makeNewSale()
    }
}
