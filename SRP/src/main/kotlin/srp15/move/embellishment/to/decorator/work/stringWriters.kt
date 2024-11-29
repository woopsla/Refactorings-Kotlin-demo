package srp15.move.embellishment.to.decorator.work

import java.util.*

//region Description
/*
 * 1. Replace Type-code with Subclasses to remove embellished logic
 *   1.1 Self-encapsulate field against type code
 *   1.2 create static create method to encapsulate type codes
 *       - return type must be the enclosed type
 *   {compile and test}
 *   1.3 for each type, create subclasses.
 *   1.4 override type code for getter to return true/false
 *   1.5 modify create method and remove type-code field, setter,
 *       ctor that accepts it.
 * 2. Replace Conditional with Polymorphism.
 *   2.1 copy to subclass embellished method and modify and compile.
 *   2.2 remove unnecessary getter for type code
 *   2.3 make subclass to call super to remove minor duplication.
 * 3. Replace Inheritance with Delegation
 *   3.1 Make a delegate field in subclass and replace super with enclosure.
 *   3.2 Extract Parameter to accept a delegate from the static create method in 1.2.
 */
//endregion

// SAM, Functional Interface
fun interface Writer {
    fun write(msg: String)
}

open class StringWriter : Writer {
    private val target = StringBuilder()

    override fun write(msg: String) {
        target.append(msg)
    }

    override fun toString(): String = target.toString()
}

class CapStringWriter(val delegate: Writer) : Writer {
    override fun write(msg: String) {
        delegate.write(msg.uppercase(Locale.getDefault()))
    }

    override fun toString(): String {
        return delegate.toString()
    }
}

class CensoredStringWriter(val delegate: Writer) : Writer {
    override fun write(msg: String) {
        delegate.write(msg.replace("Kotlin", "xxxxxx"))
    }

    override fun toString(): String {
        return delegate.toString()
    }
}

//val stringWriter: Writer = Writer { it }
fun stringWriter(buf: StringBuilder): Writer = Writer { buf.append(it) }

fun Writer.capWriter(): Writer =
    Writer { write(it.uppercase(Locale.getDefault())) }

fun Writer.censorWriter(): Writer =
    Writer { write(it.replace("Kotlin", "xxxxxx")) }