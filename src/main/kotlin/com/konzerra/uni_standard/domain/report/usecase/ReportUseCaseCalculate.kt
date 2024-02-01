package com.konzerra.uni_standard.domain.report.usecase

import com.konzerra.uni_standard.domain.report.Report

interface ReportUseCaseCalculate {

    /**
     * runs calculations for evaluation
     */
    fun execute(report: Report)
}