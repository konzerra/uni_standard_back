package com.konzerra.uni_standard.domain.report.impl

import com.konzerra.uni_standard.domain.report.Report
import com.konzerra.uni_standard.domain.report.ReportService
import com.konzerra.uni_standard.domain.report.ReportStatus
import com.konzerra.uni_standard.domain.report._evaluation.Evaluation
import com.konzerra.uni_standard.domain.report._evaluation.port.EvaluationPort
import com.konzerra.uni_standard.domain.report._evalutation_group.EvaluationGroup
import com.konzerra.uni_standard.domain.report._evalutation_group.port.EvaluationGroupPort
import com.konzerra.uni_standard.domain.report.dto.ReportResponseDto
import com.konzerra.uni_standard.domain.report.dto.ReportSaveDto
import com.konzerra.uni_standard.domain.report.dto.ReportUpdateDto
import com.konzerra.uni_standard.domain.report.port.ReportPort
import com.konzerra.uni_standard.domain.report.usecase.ReportUseCaseCalculate
import com.konzerra.uni_standard.domain.standard.criteria_group.port.CriteriaGroupPort
import com.konzerra.uni_standard.domain.standard.criterion.port.CriterionPort
import com.konzerra.uni_standard.domain.standard.port.StandardPort
import com.konzerra.uni_standard.domain.university.University
import com.konzerra.uni_standard.domain.university.port.UniversityPort
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(
    private val reportPort: ReportPort,
    private val standardPort: StandardPort,
    private val criterionPort: CriterionPort,
    private val universityPort: UniversityPort,
    private val criteriaGroupPort: CriteriaGroupPort,
    private val reportUseCaseCalculate: ReportUseCaseCalculate,
    private val evaluationGroupPort: EvaluationGroupPort,
    private val evaluationPort: EvaluationPort
) : ReportService {
    override fun findAll(lang: String): List<ReportResponseDto> {
        //
        return reportPort.findAll().map {
            ReportResponseDto.toDto(it, lang)
        }
    }


    override fun findById(id: Long, lang: String): ReportResponseDto {
        return ReportResponseDto.toDto(reportPort.findById(id), lang)
    }

    override fun deleteById(id: Long) {
        reportPort.deleteById(id)
    }

    override fun save(saveDto: ReportSaveDto) {

        //TODO deal with duplicates
        reportPort.save(
            Report(
                status = ReportStatus.REGISTERED,
                standard = standardPort.findById(saveDto.standardId),
                evaluationGroups = saveDto.evaluationGroups.map { evaluationGroupSaveDto->
                    EvaluationGroup(
                        criteriaGroup = criteriaGroupPort.findById(evaluationGroupSaveDto.criteriaGroupId),
                        evaluations = evaluationGroupSaveDto.evaluations.map {
                            Evaluation(
                                value = it.value,
                                criterion = criterionPort.findById(it.criterionId)
                                )
                        }
                    )
                },
                university = universityPort.save(
                    University().apply {
                        version = saveDto.university.version
                        name = saveDto.university.name
                        address = saveDto.university.address
                        yearFounded = saveDto.university.yearFounded
                        rectorName = saveDto.university.rectorName
                        numOfEducationalUnits = saveDto.university.numOfEducationalUnits
                        studentNumber = saveDto.university.studentNumber
                        programNumber = saveDto.university.programNumber
                        academicStaffTraining = saveDto.university.academicStaffTraining
                        numOfFirstYearStudents = saveDto.university.numOfFirstYearStudents
                        numOfGraduates = saveDto.university.numOfGraduates
                        totalAreaOfEducationalFund = saveDto.university.totalAreaOfEducationalFund
                    }
                )
            )
        )

    }

    override fun update(updateDto: ReportUpdateDto) {
        val report = reportPort.findById(updateDto.id)
        report.status = updateDto.status

        report.evaluationGroups = updateDto.evaluationGroups.map { evaluationGroupUpdateDto->
            EvaluationGroup(
                id = evaluationGroupUpdateDto.id,
                criteriaGroup = criteriaGroupPort.findById(evaluationGroupUpdateDto.criteriaGroupId),
                evaluations = evaluationGroupUpdateDto.evaluations.map {
                    Evaluation(
                        id = it.id,
                        value = it.value,
                        criterion = criterionPort.findById(it.criterionId)
                    )
                }
            )
        }

        report.university = University().apply {
                id = updateDto.id
                version = updateDto.university.version
                name = updateDto.university.name
                address = updateDto.university.address
                yearFounded = updateDto.university.yearFounded
                rectorName = updateDto.university.rectorName
                numOfEducationalUnits = updateDto.university.numOfEducationalUnits
                studentNumber = updateDto.university.studentNumber
                programNumber = updateDto.university.programNumber
                academicStaffTraining = updateDto.university.academicStaffTraining
                numOfFirstYearStudents = updateDto.university.numOfFirstYearStudents
                numOfGraduates = updateDto.university.numOfGraduates
                totalAreaOfEducationalFund = updateDto.university.totalAreaOfEducationalFund
        }

        reportPort.save(report)
        if(updateDto.status == ReportStatus.PUBLISHED){
            reportUseCaseCalculate.execute(report)
        }
    }
}