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
package dip01.encapsulate.collection.work

data class Course(private val name: String, val isAdvanced: Boolean)

class Person {
    private val _courses = mutableSetOf<Course>()
    val courses: Set<Course>
        get() = _courses

    fun addCourse(course: Course) {
        _courses.add(course)
    }

    fun removeCourse(course: Course) {
        _courses.remove(course)
    }
}

fun main(args: Array<String>) {
    // Client code
    val kent = Person()
    kent.addCourse(Course("Smalltalk Programming", false))
    kent.addCourse(Course("Appreciating Single Malts", true))
    assert(kent.courses.size == 2)

    kent.addCourse(Course("Refactoring", true))
    kent.addCourse(Course("Brutal Sarcasm", false))
    assert(kent.courses.size == 4)

    kent.removeCourse(Course("Refactoring", true))
    assert(kent.courses.size == 3)

    val count = kent.courses.count { it.isAdvanced }

    println("Courses by kent: ${kent.courses}")
    println("Advanced courses count = $count")
}