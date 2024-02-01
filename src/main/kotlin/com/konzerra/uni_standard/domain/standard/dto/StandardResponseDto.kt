package com.konzerra.uni_standard.domain.standard.dto

import com.konzerra.uni_standard.domain.standard.Standard
import com.konzerra.uni_standard.domain.standard.criteria_group.dto.CriteriaGroupResponseDto
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

class StandardResponseDto(
    var id: Long? = null,
    var name: String,
    var version: String,
    var description: String,
    var status: String,
    var criteriaGroups: List<CriteriaGroupResponseDto>,
)
{
    @Component
    companion object : Mapper<Standard, StandardResponseDto> {
        override fun toDto(entity: Standard, lang: String): StandardResponseDto {
            return StandardResponseDto(
                id = entity.id,
                name = entity.name,
                version = entity.version,
                description = entity.description,
                status = entity.status,
                criteriaGroups = entity.criteriaGroups.map { CriteriaGroupResponseDto.toDto(it, lang) },
            )
        }
    }
}