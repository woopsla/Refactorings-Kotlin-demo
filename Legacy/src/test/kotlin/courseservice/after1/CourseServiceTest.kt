package courseservice.after1

import courseservice.Course
import courseservice.UserNotLoggedInException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CourseServiceTest {
    companion object {
        val REFACTORING: Course = Course()
        val PATTERNS: Course = Course()
        val UNUSED_USER: User = User()
        val ANOTHER_USER: User = User()
        val MEMBER: User = User()
    }

    // SUT
    private lateinit var courseService: CourseService

    private var registeredUser: User? = null

    @BeforeEach
    fun setUp() {
        courseService = MockCourseService()
    }

    @Test
    fun `should throw exception if not logged-in user`() {
        // Arrange (Given)
        // Assert (Then)
        assertThrows(UserNotLoggedInException::class.java) {
            // Act (When)
            courseService.getCoursesByUser(UNUSED_USER)
        }
    }

    @Test
    fun `should return empty course list if they are not friends`() {
        // Arrange (Given)
        val user = User()
        user.addCourse(REFACTORING)
        user.addCourse(PATTERNS)
        user.addFriend(ANOTHER_USER)

        registeredUser = MEMBER

        // Act (When)
        val courses = courseService.getCoursesByUser(user)

        // Assert (Then)
        assertEquals(0, courses.size)
    }

    @Test
    fun `should return course list if they are friends`() {
        // Arrange (Given)
        courseService = MockCourseService()
        val user = User()
        user.addCourse(REFACTORING)
        user.addCourse(PATTERNS)
        user.addFriend(ANOTHER_USER)
        user.addFriend(MEMBER)

        registeredUser = MEMBER

        // Act (When)
        val courses = courseService.getCoursesByUser(user)

        // Assert (Then)
        assertEquals(2, courses.size)
    }

    inner class MockCourseService : CourseService() {
        override fun getLoggedInUser(): User? {
            return registeredUser
        }

        override fun coursesBy(user: User): List<Course> {
            return user.courses
        }
    }

}