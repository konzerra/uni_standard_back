package com.konzerra.uni_standard.domain.standard.dto

import com.konzerra.uni_standard.domain.report.dto.ReportResponseDto
import com.konzerra.uni_standard.domain.standard.Standard
import com.konzerra.uni_standard.domain.standard.criteria_group.dto.CriteriaGroupResponseDto
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

data class StandardReportsResponseDto(
    var id: Long?,
    var name: String,
    var version: String,
    var description: String,
    var status: String,
    var criteriaGroups: List<CriteriaGroupResponseDto>,
    var reports: List<ReportResponseDto>
) {
    @Component
    companion object : Mapper<Standard, StandardReportsResponseDto> {
        override fun toDto(entity: Standard, lang: String): StandardReportsResponseDto {
            return StandardReportsResponseDto(
                id = entity.id,
                name = entity.name,
                version = entity.version,
                description = entity.description,
                status = entity.status,
                criteriaGroups = entity.criteriaGroups.map { CriteriaGroupResponseDto.toDto(it, lang) },
                reports = entity.reports.map {
                    ReportResponseDto.toDto(it)
                }
            )
        }
    }
}