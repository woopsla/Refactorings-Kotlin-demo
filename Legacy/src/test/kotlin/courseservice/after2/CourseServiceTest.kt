package courseservice.after2

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
        val GUEST: User? = null
    }

    // SUT
    private lateinit var courseService: CourseService


    //    private val fakeDao: ICourseDAO = ICourseDAO(User::courses)
    private val fakeDao = User::courses
//    private lateinit var fakeDao: ICourseDAO

    @BeforeEach
    fun setup() {
        courseService = CourseService(fakeDao)
//        fakeDao = object : ICourseDAO {
//            override fun coursesBy(user: User): List<Course> {
//                return user.getCourses()
//            }
//        }
    }

    @Test
    fun `should throw exception if not logged-in user`() {
        // Arrange (Given)
        // Assert (Then)
        assertThrows(UserNotLoggedInException::class.java) {
            // Act (When)
            courseService.getCoursesByUser(UNUSED_USER, GUEST)
        }
    }

    @Test
    fun `should return empty course list if they are not friends`() {
        // Arrange (Given)
        val user = User()
        user.addCourse(REFACTORING)
        user.addCourse(PATTERNS)
        user.addFriend(ANOTHER_USER)

        // Act (When)
        val courses = courseService.getCoursesByUser(user, MEMBER)

        // Assert (Then)
        assertEquals(0, courses.size)
    }

    @Test
    fun `should return course list if they are friends`() {
        // Arrange (Given)
        val user = UserBuilder.of()
            .withCourses(REFACTORING, PATTERNS)
            .withFriends(ANOTHER_USER, MEMBER)
            .build()

        // Act (When)
        val courses = courseService.getCoursesByUser(user, MEMBER)

        // Assert (Then)
        assertEquals(2, courses.size)
    }

    class UserBuilder {
        companion object {
            fun of(): UserBuilder {
                return UserBuilder()
            }
        }

        private var friends: List<User> = mutableListOf()
        private var courses: List<Course> = mutableListOf()

        fun withFriends(vararg friends: User): UserBuilder {
            this.friends = friends.toMutableList()
            return this
        }

        fun withCourses(vararg courses: Course): UserBuilder {
            this.courses = courses.toMutableList()
            return this
        }

        fun build(): User {
            val user = User()
            friends.forEach(user::addFriend)
            courses.forEach(user::addCourse)
            return user
        }
    }
}