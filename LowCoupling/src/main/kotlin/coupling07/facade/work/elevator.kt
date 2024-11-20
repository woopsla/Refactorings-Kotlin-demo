package coupling07.facade.work

enum class Direction {
    UP, DOWN, NONE
}

interface IDoor {
    fun open()
    fun close()
}

interface IMotor {
    fun stop()
    fun move(direction: Direction)
}

class ElevatorDoor : IDoor {
    override fun open() {
        println("Elevator Door Open")
    }

    override fun close() {
        println("Elevator Door Close")
    }
}

class FloorDoor(private val floor: Int) : IDoor {
    override fun open() {
        println(floor.toString() + "th Floor Door Open")
    }

    override fun close() {
        println(floor.toString() + "th Floor Door Close")
    }
}

class ElevatorMotor : IMotor {
    override fun stop() {
        println("Elevator Motor Stop")
    }

    override fun move(direction: Direction) {
        println("Elevator Motor Move with direction $direction")
    }
}

