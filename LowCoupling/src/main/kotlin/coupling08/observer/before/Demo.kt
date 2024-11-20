package coupling08.observer.before

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.border.EmptyBorder

class Clock(private val interval: Int) {
    private var counter: Counter? = null
    private var cycler: Cycler? = null

    fun setCounter(counter: Counter?) {
        this.counter = counter
    }

    fun setCycler(cycler: Cycler?) {
        this.cycler = cycler
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
        counter?.let {
            it.advance()
            it.showOn()
        }

        cycler?.let {
            it.advance()
            it.showOn()
        }
    }
}

class Counter {
    private var value = 0
    private var ui: UI? = null

    fun advance() {
        ++value
    }

    fun setUI(ui: UI) {
        this.ui = ui
        ui.isVisible = true
    }

    fun showOn() {
        ui?.display(value.toString()) ?: println("value = $value")
    }
}

class Cycler(private val base: Int) {
    private var value = 0
    private var ui: UI? = null

    fun advance() {
        value = ++value % base
    }

    fun setUI(ui: UI) {
        this.ui = ui
        ui.isVisible = true
    }

    fun showOn() {
        ui?.display(value.toString()) ?: println("value = $value")
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
        clock.setCounter(counter)

        val cycler = Cycler(10);
        cycler.setUI(UI("Cycler"));
        clock.setCycler(cycler);

        clock.run()
    }
}
