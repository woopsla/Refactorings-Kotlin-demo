package courseservice.work

import courseservice.Course
import courseservice.UserNotLoggedInException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CourseServiceTest {
    // SUT (System Under Test)
    lateinit var courseService: CourseService

    @BeforeEach // Test Fixture
    fun setup() {
//        courseDao = ICourseDAO { user: User -> user.courses }
        courseService = CourseService { user: User -> user.courses }
    }

    @Test
    fun `should return exception if user not logged in`() {
        // Arrange (Given)

        // Assert (Then)
        assertThrows(UserNotLoggedInException::class.java) {
            // Act (When)
            courseService.getCoursesByUser(UNUSED_USER, GUEST)
        }
    }

    @Test
    fun `should return empty list if they are not friends`() {
        // Arrange (Given)
        var user = UserBuilder.of()
            .withCourses(REFACTORING, DESIGN_PATTERN)
            .withFriends(ANOTHER_USER)
            .build()

        // Act (When)
        val courses =
            courseService.getCoursesByUser(user, MEMBER)

        // Assert (Then)
        assertEquals(0, courses.size)
    }

    @Test
    fun `should return non empty list if they are friends`() {
        // Arrange (Given)
        // Fluent API
        // Builder Pattern
        var user = UserBuilder.of()
            .withCourses(REFACTORING, DESIGN_PATTERN)
            .withFriends(ANOTHER_USER, MEMBER)
            .build()

        // Act (When)
        val courses =
            courseService.getCoursesByUser(user, MEMBER)

        // Assert (Then)
        assertEquals(2, courses.size)
    }

    companion object {
        val REFACTORING: Course = Course()
        val DESIGN_PATTERN: Course = Course()
        val UNUSED_USER: User = User()
        val GUEST: User? = null
        val MEMBER: User = User()
        val ANOTHER_USER: User = User()
    }
}

class UserBuilder {
    private val friends = mutableListOf<User>()
    private val courses = mutableListOf<Course>()

    companion object {
        fun of(): UserBuilder = UserBuilder()
    }

    fun withCourses(vararg courses: Course): UserBuilder {
        this.courses.addAll(courses)
        return this
    }

    fun withFriends(vararg friends: User): UserBuilder {
        this.friends.addAll(friends)
        return this
    }

//    fun build(): User {
//        val user = User()
//        courses.forEach { user.addCourse(it) }
//        friends.forEach { user.addFriend(it) }
//        return user
//    }

    fun build(): User =
        User().also { user ->
            courses.forEach(user::addCourse) // method reference, bound callable reference
            friends.forEach(user::addFriend)
        }

//    fun build(): User = User().apply {
//        this@UserBuilder.courses.forEach(this::addCourse) // method reference
//        this@UserBuilder.friends.forEach(this::addFriend)
//    }
}
