package com.konzerra.uni_standard.domain.report.impl

import com.konzerra.uni_standard.common.pagination.PaginationMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.criterion.port.CriterionPort
import com.konzerra.uni_standard.domain.criterion_response.CriterionEvaluation
import com.konzerra.uni_standard.domain.report.Report
import com.konzerra.uni_standard.domain.report.ReportService
import com.konzerra.uni_standard.domain.report.ReportStatus
import com.konzerra.uni_standard.domain.report.dto.ReportResponseDto
import com.konzerra.uni_standard.domain.report.dto.ReportSaveDto
import com.konzerra.uni_standard.domain.report.dto.ReportUpdateDto
import com.konzerra.uni_standard.domain.report.port.ReportPort
import com.konzerra.uni_standard.domain.standard.port.StandardPort
import com.konzerra.uni_standard.domain.university.port.UniversityPort
import com.konzerra.uni_standard.domain.user.port.UserPort
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(
    private val reportPort: ReportPort,
    private val standardPort: StandardPort,
    private val criterionPort: CriterionPort,
    private val universityPort: UniversityPort,
    private val userPort: UserPort,
) : ReportService {
    override fun findAll(lang: String): List<ReportResponseDto> {
        //
        return reportPort.findAll().map {
            ReportResponseDto.toDto(it, lang)
        }
    }

    override fun findPaginatedByUser(pageRequestDto: PageRequestDto, userId: Long, lang: String): Page<ReportResponseDto> {
        return reportPort.findPaginatedByUser(PaginationMapper.toPageable(pageRequestDto), userId).map {
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
                owner = userPort.findById(saveDto.userId),
                standard = standardPort.findById(saveDto.standardId),
                evaluations = saveDto.evaluations.map {
                    CriterionEvaluation(
                        criterion = criterionPort.findById(it.criterionId),
                        value = it.value
                    )
                },
                university = universityPort.findById(saveDto.universityId)
            )
        )
    }

    override fun update(updateDto: ReportUpdateDto) {
        //TODO deal with status
        val report = reportPort.findById(updateDto.id)
        report.status = updateDto.status
        report.evaluations = updateDto.evaluations.map {
            CriterionEvaluation(
                id = it.id,
                criterion = criterionPort.findById(it.criterionId),
                value = it.value
            )
        }
        reportPort.save(report)
    }
}