package com.konzerra.uni_standard.domain.report.usecase.impl

import com.konzerra.uni_standard.annotation.UseCase
import com.konzerra.uni_standard.domain.report.Report
import com.konzerra.uni_standard.domain.report.port.ReportPort
import com.konzerra.uni_standard.domain.report.usecase.ReportUseCaseCalculate
import kotlin.math.min

@UseCase
class ReportUseCaseCalculateImpl(
    private val reportPort: ReportPort,
) : ReportUseCaseCalculate {
    override fun execute(report: Report) {
        var totalEvaluations = 0
        var sumOfCalc = 0.0
        var totalReserve = 0.0
        report.evaluationGroups.forEach {  evaluationGroup ->
            evaluationGroup.evaluations.forEach {
                totalEvaluations += 1
                val criterion = it.criterion
                if (criterion.value > 0) {
                    it.result = min(it.value / criterion.value, 1.0) // Result ratio capped to a maximum of 1.0
                    sumOfCalc += it.result
                    it.reserve = if (it.value > criterion.value) (it.value - criterion.value) / criterion.value else 0.0 // Excess ratio
                    totalReserve += it.reserve
                }
                else {
                    throw IllegalArgumentException("Criterion value must be greater than zero")
                }
            }
        }
        report.average = sumOfCalc/totalEvaluations
        report.reserve = totalReserve/totalEvaluations
        reportPort.save(report)
    }
}