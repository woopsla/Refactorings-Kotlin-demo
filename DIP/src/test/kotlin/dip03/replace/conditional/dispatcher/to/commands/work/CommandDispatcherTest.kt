package dip03.replace.conditional.dispatcher.to.commands.work

import dip03.replace.conditional.dispatcher.to.commands.Book
import dip03.replace.conditional.dispatcher.to.commands.Library
import dip03.replace.conditional.dispatcher.to.commands.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommandDispatcherTest {
    private val book = Book("The Hobbit", "J.R.R. Tolkien", "1234")
    private val user = User("John Doe", "123 Main St", "123-456-7890")

    private lateinit var dispatcher: CommandDispatcher
    private val library = Library()

    @BeforeEach
    fun setup() {
        dispatcher = CommandDispatcher(library)
    }

    @Test
    fun `add book`() {
        dispatcher.dispatchCommand("addBook The Hobbit, J.R.R. Tolkien, 1234")
        assertEquals(1, library.documents().size)
        val returnedBook = library.documents()[0] as Book
        assertAll(
            "book",
            { assertEquals(book.title, returnedBook.title) },
            { assertEquals(book.authors, returnedBook.authors) },
            { assertEquals(book.iSBN, returnedBook.iSBN) },
        )
    }

    @Test
    fun `add user`() {
        dispatcher.dispatchCommand("addUser John Doe, 123 Main St, 123-456-7890")
        assertEquals(1, library.users().size)
        assertEquals(user, library.users()[0])
    }
}