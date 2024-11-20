package coupling07.facade.work

const val MAX_FLOOR = 10

fun main(args: Array<String>) {
    val floorDoors: List<FloorDoor> =
        Array(MAX_FLOOR) { i -> FloorDoor(i + 1) }.toList()

    val controller = ElevatorController(
        ElevatorMotor(), ElevatorDoor(), floorDoors, DoorTimer()
    )

    controller.goTo(4)
    controller.approaching(2)
    controller.approaching(3)
    controller.approaching(4)
}

