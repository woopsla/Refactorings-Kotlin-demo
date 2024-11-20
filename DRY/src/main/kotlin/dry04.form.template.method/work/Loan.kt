/*
 * SMELL: Similar Codes in Subclasses
 *   -- Two methods in subclasses perform similar steps in the same order, 
 *      yet the steps are different. 
 * 
 * TREATMENT: Form Template Method
 *   -- Generalize the methods by extracting their steps into methods with 
 *      identical signatures, then pull up the generalized methods to form 
 *      a "Template Method".  
 */
package dry04.form.template.method.work

import java.time.LocalDate

/*
 * The loan is a term loan, a revolver, or an advised line.
 * -- A term loan is a loan that must be fully paid by its maturity date.
 * -- A revolver, which is like a credit card, is a loan that signifies 
 * 	  “revolving credit”: you have a spending limit and an expiry date.
 * -- A revolving credit term loan (RCTL) is a revolver that transforms 
 *    into a term loan when the revolver expires.
 * 	  RCTL needs both an expiry date and a maturity date
 */
class Loan
private constructor(
    val commitment: Double,
    private val outstanding: Double, // outstandingRiskAmount
    val riskRating: Int,
    val maturity: LocalDate? = null,
    val expiry: LocalDate? = null
) {
    val today: LocalDate? = null
    val start: LocalDate? = null
    val payments: List<Payment> = mutableListOf()

    private var capitalStrategy: CapitalStrategy = expiry?.let {
        maturity?.let { RCTLCapitalStrategy() } ?: RevolverCapitalStrategy()
    } ?: TermLoanCapitalStrategy()

    fun capital(): Double = capitalStrategy.capital(this)

    fun getOutstandingRiskAmount(): Double {
        return outstanding
    }

    fun unusedRiskAmount(): Double = commitment - outstanding

    val unusedPercentage: Double = TODO()

    companion object {
        // Term Loan (expiry == null)
        fun createTermLoan(
            commitment: Double,
            outstanding: Double,
            riskRating: Int,
            maturity: LocalDate
        ): Loan {
            return Loan(commitment, outstanding, riskRating, maturity, null)
        }

        // Revolver (maturity == null)
        fun createRevolver(
            commitment: Double,
            outstanding: Double,
            riskRating: Int,
            expiry: LocalDate
        ): Loan {
            return Loan(commitment, outstanding, riskRating, null, expiry)
        }

        // RCTL (expiry != null && maturity &= null)
        fun createRCTL(
            commitment: Double,
            outstanding: Double,
            riskRating: Int,
            maturity: LocalDate,
            expiry: LocalDate
        ): Loan {
            return Loan(commitment, outstanding, riskRating, maturity, expiry)
        }
    }
}

data class Payment(val amount: Double, val date: LocalDate)

object RiskFactor {
    fun getFactors(): Factors = TODO()
}

object UnusedRiskFactors {
    fun getFactors(): Factors = TODO()
}

class Factors {
    fun forRating(riskRating: Int): Double = TODO()
}
