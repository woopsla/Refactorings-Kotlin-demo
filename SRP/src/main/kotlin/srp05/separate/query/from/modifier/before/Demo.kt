/*
 * SMELL: Poor Cohesion
 *   -- You have a method that returns a value but also changes 
 *      the state of the object.
 * 
 * TREATMENT: Separate Query from Modifier
 *   -- Create two methods, one for the query and one for the modification.
 *   -- The query method can be reused independently.
 */
package srp05.separate.query.from.modifier.before

class Demo {
    private val scores = mutableListOf<Int>()

    fun addAndGetSum(score: Int): Long {
        scores.add(score)
        var sum: Long = 0
        for (i in scores) sum += i.toLong()
        return sum
    }
}

fun main() {
    // Client code
    val demo = Demo()

    for (i in 0..9) {
        val sum = demo.addAndGetSum(i)
        println("sum = $sum")
    }
}

