package com.konzerra.uni_standard.domain.report.dto

import com.konzerra.uni_standard.domain.criterion_response.dto.CriterionEvaluationResponseDto
import com.konzerra.uni_standard.domain.report.Report
import com.konzerra.uni_standard.domain.standard.dto.StandardResponseDto
import com.konzerra.uni_standard.domain.university.dto.UniversityResponseDto
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

data class ReportResponseDto(
    var id: Long? = null,
    var status: String,
    var standard: StandardResponseDto,
    var evaluations: List<CriterionEvaluationResponseDto>,
    var university: UniversityResponseDto
) {
    @Component
    companion object : Mapper<Report, ReportResponseDto> {
        override fun toDto(entity: Report, lang: String): ReportResponseDto {
            return ReportResponseDto(
                id = entity.id,
                status = entity.status,
                standard = StandardResponseDto.toDto(entity.standard, lang),
                evaluations = entity.evaluations.map { CriterionEvaluationResponseDto.toDto(it, lang) },
                university = UniversityResponseDto.toDto(entity.university, lang)
            )
        }
    }
}