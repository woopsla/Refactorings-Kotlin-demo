/*
 * SMELL: Switch Statement
 *   -- Conditional logic is used to dispatch requests and execute actions. 
 *
 * TREATMENT: Replace Conditional Dispatcher with Command
 *   -- Create a Command for each action. 
 *   -- Store the Commands in a collection and replace the conditional 
 *      logic with code to fetch and execute Commands. 
 *
 */
package dip03.replace.conditional.dispatcher.to.commands.before

import dip03.replace.conditional.dispatcher.to.commands.Book
import dip03.replace.conditional.dispatcher.to.commands.Document
import dip03.replace.conditional.dispatcher.to.commands.Library
import dip03.replace.conditional.dispatcher.to.commands.User

class CommandDispatcher(private var library: Library = Library()) {
    private fun printHeader() {
        println("COMMANDS:")
        println("addUser name, address, phone")
        println("addBook title, authors, ISBN")

        //region Add more commands
        println("addlntUser name, address, phone, id")
        println("rmUser userld")
        println("addReport title, ref, authors")
        println("addJournal title")
        println("rmDoc docid")
        println("borrowDoc userld, docId")
        println("returnDoc docId")
        println("searchUser name")
        println("searchDoc title")
        println("isHolding userld, docId")
        println("printLoans")
        println("printUser userld")
        //endregion

        println("printDoc docId")
        println("exit")
    }

    private fun getArgs(cmdline: String): Array<String> {
        var args = emptyArray<String>()
        var command = cmdline.trim { it == ' ' }
        if (command.indexOf(" ") != -1) {
            command = command.substring(command.indexOf(" "))
            args = command.trim { it == ' ' }.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (i in args.indices) args[i] = args[i].trim { it == ' ' }
        }
        return args
    }

    /*
	 * Conditional-dispatcher
	 */
    fun dispatchCommand(cmd: String) {
        val args = getArgs(cmd)
        when {
            cmd.startsWith("addUser") -> {
                if (args.size < 3) return
                val user = User(args[0], args[1], args[2])
                library.addUser(user)
                println("Added user: " + user.name + " - " + user.address + " - " + user.phone)
            }

            cmd.startsWith("addBook") -> {
                if (args.size < 3) return
                val doc: Document = Book(args[0], args[1], args[2])
                library.addDocument(doc)
                println("Added doc: " + doc.code + " - " + doc.title)
            }
        }

        /* 
		 * else if .........
		 */
    }
}
