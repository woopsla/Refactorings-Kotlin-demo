package courseservice.work

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

    // Tell, Don't Ask => Feature Envy => Move Method
    // Replace Algorithm
    fun isFriendWith(aUser: User): Boolean =
        //        return friends.find { it == aUser } != null
        friends.contains(aUser)

//        var isFriend = false
//        for (friend in friends) {
//            if (friend == loggedUser) {
//                isFriend = true
//                break
//            }
//        }
//        return isFriend
}
