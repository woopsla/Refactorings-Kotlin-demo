package courseservice.work

import courseservice.Course
import courseservice.UserNotLoggedInException

open class CourseService(val courseDAO: ICourseDAO) {
    fun getCoursesByUser(user: User, loggedInUser: User?): List<Course?> {
        if (loggedInUser == null) throw UserNotLoggedInException()

        return if (user.isFriendWith(loggedInUser)) {
            getCoursesBy(user)
        } else {
            emptyList<Course>()
        }
    }

    protected open fun getCoursesBy(user: User): List<Course> =
        courseDAO.getCoursesBy(user)
}