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
package scp06.introduce.assertions.before

class Project(var memberExpenseLimit: Double = 1234.0)

class Demo {
    private lateinit var primaryProject: Project
    private var expenseLimit: Double = 0.0;

    // should have either expense limit or a primary project
    fun expenseLimit(): Double =
        if ((expenseLimit != NULL_EXPENSE)) expenseLimit else primaryProject.memberExpenseLimit

    companion object {
        private const val NULL_EXPENSE = 0.0
    }
}
