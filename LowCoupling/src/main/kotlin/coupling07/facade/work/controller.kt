package coupling07.facade.work

class ElevatorController(
    private val motor: IMotor,
    private val elevatorDoor: ElevatorDoor,
    private val floorDoors: List<FloorDoor>,
    private val doorTimer: DoorTimer
) {
    private var curFloor = 1
    private val destinations = mutableListOf<Int>()
    private var isMoving = false

    fun goTo(destination: Int) {
        if (!destinations.contains(destination)) destinations.add(destination)
        if (isMoving == false) {
            determineMovingDirection().let { direction ->
                if (direction != Direction.NONE) {
                    motor.move(direction)
                    isMoving = true
                }
            }
        }
    }

    fun approaching(floor: Int) {
        println(
            """
                
                Approaching ${floor}th floor
                """.trimIndent()
        )
        curFloor = floor
        if (needToStop(floor)) {
            motor.stop()
            isMoving = false

            elevatorDoor.open()
            floorDoors[curFloor - 1].open()
            destinations.remove(floor)
            doorTimer.start(this)
        }
    }

    private fun needToStop(floor: Int): Boolean = destinations.contains(floor)

    fun doorTimeout() {
        elevatorDoor.close()
        floorDoors[curFloor - 1].close()
        doorTimer.stop()
        determineMovingDirection().let { direction ->
            if (direction != Direction.NONE) {
                motor.move(direction)
                isMoving = true
            }
        }
    }

    private fun determineMovingDirection(): Direction {
        if (destinations.size == 0) return Direction.NONE
        return if (destinations[0] > curFloor) Direction.UP else Direction.DOWN
    }

    fun openDoor() {
        if (isMoving) return

        elevatorDoor.open()
        floorDoors[curFloor - 1].open()
        doorTimer.start(this)
        destinations.remove(curFloor)
    }

    fun closeDoor() {
        if (isMoving) return

        elevatorDoor.close()
        floorDoors[curFloor - 1].close()
        doorTimer.stop()
    }
}


