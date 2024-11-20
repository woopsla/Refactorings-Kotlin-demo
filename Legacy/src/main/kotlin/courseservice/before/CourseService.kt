package courseservice.before

import courseservice.Course
import courseservice.UserNotLoggedInException

class CourseService {
    fun getCoursesByUser(user: User): List<Course?> {
        var courseList: List<Course> = emptyList()
        val loggedUser = UserSession.getInstance().getLoggedUser()
        var isFriend = false

        if (loggedUser != null) {
            for (friend in user.friends) {
                if (friend == loggedUser) {
                    isFriend = true
                    break
                }
            }
            if (isFriend) {
                courseList = CourseDAO.findCoursesByUser(user)
            }
            return courseList
        } else {
            throw UserNotLoggedInException()
        }
    }
}