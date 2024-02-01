package com.konzerra.uni_standard.report

import com.konzerra.uni_standard.domain.standard.criterion.Criterion
import com.konzerra.uni_standard.domain.report._evaluation.Evaluation
import com.konzerra.uni_standard.domain.report.Report
import com.konzerra.uni_standard.domain.report.port.ReportPort
import com.konzerra.uni_standard.domain.report.usecase.ReportUseCaseCalculate
import com.konzerra.uni_standard.domain.report.usecase.impl.ReportUseCaseCalculateImpl
import com.konzerra.uni_standard.domain.standard.Standard
import com.konzerra.uni_standard.domain.standard.port.StandardPort
import com.konzerra.uni_standard.domain.university.University
import com.konzerra.uni_standard.domain.user.User

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReportUseCaseCalculateImplTest {
    private lateinit var reportPort: ReportPort
    private lateinit var standardPort: StandardPort
    private lateinit var user: User
    private lateinit var standard: Standard
    private lateinit var university: University

    private lateinit var reportUseCaseCalculate: ReportUseCaseCalculate

    @BeforeEach
    fun setup() {
        reportPort = mockk(relaxed = true)
        standardPort = mockk(relaxed = true)
        user = mockk(relaxed = true)
        standard = mockk(relaxed = true)
        university = mockk(relaxed = true)

        reportUseCaseCalculate = ReportUseCaseCalculateImpl(reportPort)
    }

    @Test
    fun `execute should calculate evaluation values and save report`() {
//        // Arrange
//        val criterion = Criterion(1, "name","description", 25000.0)
//        val evaluation = Evaluation(1, criterion, 20000.0, 0.0, 0.0)
//        val report = Report(1, "status", user, standard, listOf(evaluation), university)
//
//        // Act
//        reportUseCaseCalculate.execute(report)
//
//        // Assert
//        verify { reportPort.save(any()) }
//        println(evaluation.result)
//        println(evaluation.reserve)
//        assert(evaluation.result == 0.8)
//        assert(evaluation.reserve == 0.0)
    }

    @Test
    fun `execute should calculate evaluation values with reserve`() {
//        // Arrange
//        val criterion = Criterion(1, "name","description", 20000.0)
//        val evaluation = Evaluation(1, criterion, 25000.0, 0.0, 0.0)
//        val report = Report(1, "status", user, standard, listOf(evaluation), university)
//
//        // Act
//        reportUseCaseCalculate.execute(report)
//
//        // Assert
//        verify { reportPort.save(any()) }
//        println(evaluation.result)
//        println(evaluation.reserve)
//        assert(evaluation.result == 1.0)
//        assert(evaluation.reserve == 0.25)
    }

    @Test
    fun `execute should calculate evaluation values with reserve equal to zero`() {
//        // Arrange
//        val criterion = Criterion(1, "name","description", 20000.0)
//        val evaluation = Evaluation(1, criterion, 20000.0, 0.0, 0.0)
//        val report = Report(1, "status", standard, listOf(evaluation), university)
//
//        // Act
//        reportUseCaseCalculate.execute(report)
//
//        // Assert
//        verify { reportPort.save(any()) }
//        println(evaluation.result)
//        println(evaluation.reserve)
//        assert(evaluation.result == 1.0)
//        assert(evaluation.reserve == 0.0)
    }
}