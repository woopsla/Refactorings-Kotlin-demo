/*
 * SMELL: Long Method
 *   -- You can’t rapidly understand a method’s logic. 
 *   -- It consists of a number of concrete logics.
 * 
 * TREATMENT: Compose Method
 *   -- Transform the logic into a small number of 
 *      intention-revealing steps at the same level of detail.
 */
package srp03.compose.method.work

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
        if (readOnly) return

        if (updateSize() > elements.size) { // grow array if capacity exceeds
            growCapacity()
        }
        addElement(element) // add element
    }

    private fun addElement(element: T) {
        elements[size++] = element
    }

    private fun growCapacity() {
        val newElements = arrayOfNulls<Any>(elements.size + 10) as Array<T>
        for (i in 0 until size) newElements[i] = elements[i]
        elements = newElements
    }

    private fun updateSize(): Int = size + 1

    val capacity: Int
        get() = elements.size
}
