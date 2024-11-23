package coupling08.observer.before

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.border.EmptyBorder

class Clock(private val interval: Int) {
    var counter: Counter? = null
    var cycler: Cycler? = null

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
        counter?.run {
            advance()
            showOn()
        }

        cycler?.run {
            advance()
            showOn()
        }
    }
}

class Counter {
    private var value = 0
    var ui: UI? = null
        set(value) {
            field = value?.apply {
                isVisible = true
            }
        }

    fun advance() {
        ++value
    }

    fun showOn() {
        ui?.display(value.toString()) ?: println("value = $value")
    }
}

class Cycler(private val base: Int) {
    private var value = 0
    var ui: UI? = null
        set(value) {
            field = value?.apply {
                isVisible = true
            }
        }

    fun advance() {
        value = ++value % base
    }

    fun showOn() {
        ui?.display(value.toString()) ?: println("value = $value")
    }
}

class UI(private val title: String) : JFrame() {
    private val text: JTextField = JTextField(10)

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setBounds(100, 100, 150, 80)

        setContentPane(JPanel().apply {
            border = EmptyBorder(5, 5, 5, 5)
            layout = BorderLayout()
            add("West", JLabel("$title: "))
            add("Center", text)
        })
        isVisible = true
    }

    fun display(value: String?) {
        text.text = value
    }
}

object Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        Clock(1000).apply {
            counter = Counter().apply {
                ui = UI("Counter")
            }
            cycler = Cycler(10).apply {
                ui = UI("Cycler")
            }
        }.run()
    }
}
