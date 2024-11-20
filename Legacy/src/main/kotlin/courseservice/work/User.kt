package courseservice.work

import courseservice.Course

class User {
    private val _courses = mutableListOf<Course>()
    val friends: List<User> get() = _friends

    private val _friends = mutableListOf<User>()
    val courses: List<Course> get() = _courses

    fun addFriend(user: User) {
        _friends.add(user)
    }

    fun addCourse(course: Course) {
        _courses.add(course)
    }
}
