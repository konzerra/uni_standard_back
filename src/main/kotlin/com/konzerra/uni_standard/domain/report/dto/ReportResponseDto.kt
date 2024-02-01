package com.konzerra.uni_standard.domain.report.dto

import com.konzerra.uni_standard.domain.report.Report
import com.konzerra.uni_standard.domain.report._evalutation_group.dto.EvaluationGroupResponseDto
import com.konzerra.uni_standard.domain.standard.dto.StandardResponseDto
import com.konzerra.uni_standard.domain.university.dto.UniversityResponseDto
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

data class ReportResponseDto(
    var id: Long? = null,
    var status: String,
    var standard: StandardResponseDto,
    var evaluationGroups: List<EvaluationGroupResponseDto>,
    var university: UniversityResponseDto,
    var average: Double,
    var reserve: Double
) {
    @Component
    companion object : Mapper<Report, ReportResponseDto> {
        override fun toDto(entity: Report, lang: String): ReportResponseDto {
            return ReportResponseDto(
                id = entity.id,
                status = entity.status,
                standard = StandardResponseDto.toDto(entity.standard, lang),
                evaluationGroups = entity.evaluationGroups.map { EvaluationGroupResponseDto.toDto(it, lang) },
                university = UniversityResponseDto.toDto(entity.university, lang),
                average = entity.average,
                reserve = entity.reserve
            )
        }
    }
}