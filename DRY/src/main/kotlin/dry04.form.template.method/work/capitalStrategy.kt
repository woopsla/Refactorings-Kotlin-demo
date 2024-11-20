package dry04.form.template.method.work

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

abstract class CapitalStrategy {
    abstract fun capital(loan: Loan): Double

    protected open fun duration(loan: Loan): Double = TODO()

    protected open fun riskFactor(loan: Loan): Double =
        RiskFactor.getFactors().forRating(loan.riskRating)

    protected open fun yearsTo(loan: Loan, endDate: LocalDate): Double {
        val beginDate = loan.today ?: loan.start
        return ChronoUnit.DAYS.between(beginDate, endDate).toDouble()
    }
}

class RCTLCapitalStrategy : CapitalStrategy() {
    override fun capital(loan: Loan): Double {
        return loan.commitment * loan.unusedPercentage * duration(loan) * riskFactor(loan)
    }

    override fun duration(loan: Loan): Double {
        return yearsTo(loan, loan.expiry!!)
    }
}

class RevolverCapitalStrategy : CapitalStrategy() {
    override fun capital(loan: Loan): Double {
        return ((loan.getOutstandingRiskAmount() * duration(loan) * riskFactor(loan))
                + (loan.unusedRiskAmount() * duration(loan) * unusedRiskFactor(loan)))
    }

    private fun unusedRiskFactor(loan: Loan): Double {
        return UnusedRiskFactors.getFactors().forRating(loan.riskRating)
    }
}

class TermLoanCapitalStrategy : CapitalStrategy() {
    override fun capital(loan: Loan): Double {
        return loan.commitment * duration(loan) * riskFactor(loan)
    }

    override fun duration(loan: Loan): Double {
        return weightedAverageDuration(loan)
    }

    private fun weightedAverageDuration(loan: Loan): Double {
        var duration = 0.0
        var weightedAverage = 0.0
        var sumOfPayments = 0.0

        for (payment in loan.payments) {
            sumOfPayments += payment.amount
            weightedAverage += yearsTo(loan, payment.date) * payment.amount
        }

        if (loan.commitment != 0.0) duration = weightedAverage / sumOfPayments
        return duration
    }
}

