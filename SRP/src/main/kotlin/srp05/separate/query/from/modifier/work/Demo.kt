/*
 * SMELL: Poor Cohesion
 *   -- You have a method that returns a value but also changes 
 *      the state of the object.
 * 
 * TREATMENT: Separate Query from Modifier
 *   -- Create two methods, one for the query and one for the modification.
 *   -- The query method can be reused independently.
 */
package srp05.separate.query.from.modifier.work

// CQR (Command Query Responsibility)
// CQRS (Command Query Responsibility Segregation)
class Demo {
    private val scores = mutableListOf<Int>()

    fun addScore(score: Int) {
        scores.add(score)
    }

    fun getSum(): Long = scores.sum().toLong()
}

fun main() {
    // Client code
    val demo = Demo()

    for (i in 0..9) {
        demo.addScore(i)
        val sum = demo.getSum()
        println("sum = $sum")
    }
}

