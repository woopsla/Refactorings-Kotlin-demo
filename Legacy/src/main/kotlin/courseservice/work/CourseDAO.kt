package courseservice.work

import courseservice.CollaboratorCallException
import courseservice.Course

object CourseDAO {
    @JvmStatic
    fun findCoursesByUser(user: User): List<Course> {
        throw CollaboratorCallException(
            "CourseDAO should not be invoked on an unit test."
        )
    }
}