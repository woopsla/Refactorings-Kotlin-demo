/*
 * SMELL: Inappropriate Intimacy
 *   -- One class uses the internal fields and methods of another class.
 *   -- A class contains a collection field and a simple getter and setter 
 *      for working with the collection.
 *
 * TREATMENT: Encapsulate Collection
 *   -- Make the getter-returned value read-only and create methods for 
 *      adding/deleting elements of the collection.
 */
package dip01.encapsulate.collection.before

data class Course(private val name: String, val isAdvanced: Boolean)

class Person {
    var courses = mutableSetOf<Course>()
}

fun main(args: Array<String>) {
    // Client code
    val kent = Person()
    val s = mutableSetOf<Course>()
    s.add(Course("Smalltalk Programming", false))
    s.add(Course("Appreciating Single Malts", true))
    kent.courses = s
    assert(kent.courses.size == 2)

    kent.courses.add(Course("Refactoring", true))
    kent.courses.add(Course("Brutal Sarcasm", false))
    assert(kent.courses.size == 4)

    kent.courses.remove(Course("Refactoring", true))
    assert(kent.courses.size == 3)

    var count = 0
    for (course in kent.courses) {
        if (course.isAdvanced) {
            count++
        }
    }

    println("Courses by kent: ${kent.courses}")
    println("Advanced courses count = $count")
}