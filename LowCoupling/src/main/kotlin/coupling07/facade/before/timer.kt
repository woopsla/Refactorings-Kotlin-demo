package coupling07.facade.before

import java.util.*

class DoorTimerTask(private val controller: ElevatorController) : TimerTask() {
    override fun run() = controller.doorTimeout()
}

class Timer {
    fun schedule(task: DoorTimerTask, i: Int) {
        try {
            Thread.sleep(i.toLong())
            task.run()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun cancel() {}
}

class DoorTimer {
    private val timer = Timer()

    fun start(controller: ElevatorController) {
        val task = DoorTimerTask(controller)
        timer.schedule(task, 1000)
    }

    fun stop() = timer.cancel()
}