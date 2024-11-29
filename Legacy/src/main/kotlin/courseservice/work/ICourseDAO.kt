package courseservice.work

import courseservice.Course

fun interface ICourseDAO {
    fun getCoursesBy(user: User): List<Course>
}
