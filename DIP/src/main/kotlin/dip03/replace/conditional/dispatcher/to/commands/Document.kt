package dip03.replace.conditional.dispatcher.to.commands

open class Document protected constructor(val title: String) {
    val code: Int = nextDocumentCode++

    companion object {
        private var nextDocumentCode = 0

        // dirty hack
        fun resetNextDocumentCode() {
            nextDocumentCode = 0
        }
    }
}
