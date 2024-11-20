package dip03.replace.conditional.dispatcher.to.commands

class Library {
    private val users = mutableListOf<User>()
    private val documents = mutableListOf<Document>()

    fun addUser(user: User) {
        users.add(user)
    }

    val userCount: Int = users.size

    fun addDocument(document: Document) {
        documents.add(document)
    }

    val documentCount: Int = documents.size

    fun documents(): List<Document> = documents.toList()
    fun users(): List<User> = users.toList()

    fun findUsersByName(name: String): List<User> =
        users.asSequence().filter { user -> user.name == name }.toList()

    fun findDocumentByCode(code: Int): Document? =
        documents.find { d: Document -> d.code == code }
}
