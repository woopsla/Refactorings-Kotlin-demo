package dip03.replace.conditional.dispatcher.to.commands

class Book(title: String, val authors: String, val iSBN: String) : Document(title) {
    override fun toString(): String = "Book($title, $authors, $iSBN)"
}
