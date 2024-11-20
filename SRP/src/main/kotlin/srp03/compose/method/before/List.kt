/*
 * SMELL: Long Method
 *   -- You can’t rapidly understand a method’s logic. 
 *   -- It consists of a number of concrete logics.
 * 
 * TREATMENT: Compose Method
 *   -- Transform the logic into a small number of 
 *      intention-revealing steps at the same level of detail.
 */
package srp03.compose.method.before

@Suppress("UNCHECKED_CAST")
class List<T> {
    var size: Int
    private val readOnly = false
    private var elements: Array<T>

    init {
        elements = arrayOfNulls<Any>(0) as Array<T>
        size = elements.size
    }

    fun add(element: T) {
        if (!readOnly) {
            val newSize = size + 1 // update size

            if (newSize > elements.size) { // grow array if capacity exceeds
                val newElements = arrayOfNulls<Any>(elements.size + 10) as Array<T>
                for (i in 0 until size) newElements[i] = elements[i]
                elements = newElements
            }

            elements[size++] = element // add element
        }
    }

    val capacity: Int
        get() = elements.size
}
