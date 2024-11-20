package srp06.move.accumulation.to.collecting.parameter.work

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TagNodeTest {
    @Test
    fun testToString() {
        val node1 = TagNode("html", "", "")
        val node2 = TagNode("head", "", "")
        val node3 = TagNode("style", "", "")

        /*
		 * <html >
		 *     <head >
		 *     	    <style >
		 *              <dummy width=10>Hello </dummy>
		 *     	    </style>
		 *     </head>
		 * </html>
		 */
        node1.addNode(node2)
        node2.addNode(node3)
        node3.addNode(TagNode("dummy", "width=10", "Hello"))

        assertEquals(
            "<html ><head ><style ><dummy width=10>Hello</dummy></style></head></html>",
            node1.toString()
        )
    }
}
