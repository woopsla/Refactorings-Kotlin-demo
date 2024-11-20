/*
 * SMELL: Long Method
 *   -- You have a single bulky method that accumulates 
 *      information to a local variable.
 *
 * TREATMENT: Move Accumulation to Collecting Parameter + Composed Method
 *   -- Accumulate results to a "Collecting Parameter" that 
 *      gets passed to extracted methods.
 *   -- A "Collecting Parameter" is an object that you pass to 
 *      methods in order to collect information from those methods.
 *   -- Collecting Parameters are programmed to accumulate data 
 *      from specific classes with specific interfaces.
 *   -- (1) Helps transform bulky methods into smaller, simpler methods.
 *      (2) Can make resulting code run faster.
 */
package srp06.move.accumulation.to.collecting.parameter.before

class TagNode(
    private val tagName: String,
    private val attributes: String,
    private val value: String
) {
    private val children = mutableListOf<TagNode>()

    fun addNode(node: TagNode) {
        children.add(node)
    }

    // Accumulation method ---> transform to
    // (1) initialize Collecting parameter
    // (2) pass it to the first composed method
    // (3) get the result
    override fun toString(): String {
        var result = ""
        result += "<$tagName $attributes>" // write opening Tag

        for (node in children) { // write children tag
            result += node.toString()
        }
        if (value != "") result += value

        result += "</$tagName>" // write closing tag

        return result
    }
}
