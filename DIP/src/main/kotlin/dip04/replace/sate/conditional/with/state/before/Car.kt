/*
 * 0. Self-encapsulate type field
 * 1. Define abstract State base class
 *    1.1 define abstract type code getter method
 *       - each subclass will return extant symbolic constants
 * 	  1.2 define static create method using type code (or may define
 * 	      separate create methods for each type)
 * 2. Connect host class and state subclasses by changing type code to
 *    state and modifying access methods for type code and constructors.
 * 3. Modify setters using factory method.
 * 4. Modify the name of accessors to clarify returning type code
 *    (Accessors return type codes, yet)
 * 5. Move symbolic constants to state hierarchy.
 * 6. Replace conditional with polymorphism.
 */
package dip04.replace.sate.conditional.with.state.before

class Car {
    var speed: Int = 0

    var state: CarState = CarState.Normal
        private set

    fun speedUp(targetSpeed: Int) {
        if (state == CarState.Normal) {
            if (targetSpeed > speed) {
                speed = targetSpeed
            }
        } else {
            speed = targetSpeed.coerceAtMost(LIMP_MODE_MAX_SPEED)
        }
    }

    fun speedDown(targetSpeed: Int) {
        if (speed > targetSpeed) {
            speed = targetSpeed
            return
        }
    }

    fun engineFaultDetected() {
        if (state == CarState.Normal) {
            state = CarState.Limp
            speed = LIMP_MODE_MAX_SPEED
        }
    }

    fun engineRepaired() {
        if (state == CarState.Limp) {
            state = CarState.Normal
        }
    }

    companion object {
        enum class CarState {
            Normal, Limp
        }

        const val LIMP_MODE_MAX_SPEED = 60
    }
}
