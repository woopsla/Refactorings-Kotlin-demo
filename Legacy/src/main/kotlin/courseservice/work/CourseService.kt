package courseservice.work

import courseservice.Course
import courseservice.UserNotLoggedInException

/**
 * Do we have to perform integration test instead of unit test?
 */
// Long method? Clean code?
// How many test cases we need to achieve 100% coverage? => Cyclomatic complexity (5)
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
//        CourseDAO.findCoursesByUser(user)
        courseDAO.getCoursesBy(user)

    //    protected open fun getLoggedInUser(): User? =

//    protected open fun getLoggedInUser(): User? =
//        UserSession.getInstance().getLoggedUser()
}