package courseservice.before

import courseservice.Course

class User {
    private val _courses = mutableListOf<Course>()
    val courses: List<Course> get() = _courses

    private val _friends = mutableListOf<User>()
    val friends: List<User> get() = _friends

    fun addFriend(user: User) {
        _friends.add(user)
    }

    fun addCourse(course: Course) {
        _courses.add(course)
    }
}
