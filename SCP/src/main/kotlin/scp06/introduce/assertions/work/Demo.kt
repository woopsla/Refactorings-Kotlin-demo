/*
 * SMELL: Implicit Assertion, Complex Conditional Expression
 *   -- For a portion of code to work correctly, 
 *      certain conditions or values must be true.
 *   -- A section of code assumes something about the state of 
 *      the program. Sometimes the assumptions are stated with a comment
 * 
 * TREATMENT: Introduce Assertion
 *   -- Make the assumption explicit with an assertion.
 */
package scp06.introduce.assertions.work

class Project(var memberExpenseLimit: Double = 1234.0)

class Demo {
    var primaryProject: Project? = null
    var expenseLimit: Double = 0.0;

    // should have either expense limit or a primary project
    fun expenseLimit(): Double {
        require(expenseLimit != NULL_EXPENSE || primaryProject != null) {
            "Should have either expense limit or a primary project"
        }

        return if ((expenseLimit != NULL_EXPENSE))
            expenseLimit
        else
            primaryProject!!.memberExpenseLimit
    }

    companion object {
        private const val NULL_EXPENSE = 0.0
    }
}

fun main() {
    val project = Project(1000.0)

    val demo = Demo()
    demo.expenseLimit = 2000.0
    println(demo.expenseLimit())

    demo.expenseLimit = 0.0
    demo.primaryProject = project
    println(demo.expenseLimit())

    demo.expenseLimit = 0.0
    demo.primaryProject = null
    println(demo.expenseLimit())
}