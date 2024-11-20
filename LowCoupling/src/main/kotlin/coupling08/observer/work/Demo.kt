package coupling08.observer.work

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.border.EmptyBorder

abstract class DisplayableNumber {
    private var ui: UI? = null
    protected var value: Int = 0

    fun next() {
        advance()
        showOn()
    }

    fun setUI(ui: UI) {
        this.ui = ui
        ui.isVisible = true
    }

    abstract fun advance()

    fun showOn() {
        ui?.display(value.toString()) ?: println("value = $value")
    }
}

class Clock(private val interval: Int) {
    private val numbers = mutableListOf<DisplayableNumber>()

    fun register(number: DisplayableNumber) {
        numbers.add(number)
    }

    fun unregister(number: DisplayableNumber) {
        numbers.remove(number)
    }

    fun run() {
        while (true) {
            try {
                Thread.sleep(interval.toLong())
                tick()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private fun tick() {
        numbers.forEach {
            it.advance()
            it.showOn()
        }
    }
}

class Counter : DisplayableNumber() {
    override fun advance() {
        ++value
    }
}

class Cycler(private val base: Int) : DisplayableNumber() {
    override fun advance() {
        value = ++value % base
    }
}

class UI(private val title: String) : JFrame() {
    private val contentPane: JPanel
    private val text: JTextField

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setBounds(100, 100, 150, 80)

        contentPane = JPanel()
        contentPane.border = EmptyBorder(5, 5, 5, 5)
        contentPane.layout = BorderLayout()
        setContentPane(contentPane)

        text = JTextField(10)
        contentPane.add("West", JLabel("$title: "))
        contentPane.add("Center", text)
        isVisible = true
    }

    fun display(value: String?) {
        text.text = value
    }
}

object Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        val clock = Clock(1000)

        val counter = Counter()
        counter.setUI(UI("Counter"))
        clock.register(counter)

        val cycler = Cycler(10);
        cycler.setUI(UI("Cycler"));
        clock.register(cycler);

        clock.run()
    }
}
