package com.konzerra.uni_standard.domain.report._evalutation_group.dto

import com.konzerra.uni_standard.domain.report._evaluation.dto.EvaluationResponseDto
import com.konzerra.uni_standard.domain.report._evalutation_group.EvaluationGroup
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

data class EvaluationGroupResponseDto(
    var id: Long? = null,
    var criteriaGroup:CriteriaGroup,
    var evaluations: List<EvaluationResponseDto>


) {
    @Component
    companion object : Mapper<EvaluationGroup, EvaluationGroupResponseDto> {
        override fun toDto(entity: EvaluationGroup, lang: String): EvaluationGroupResponseDto {
            return EvaluationGroupResponseDto(
                id = entity.id,
                criteriaGroup = CriteriaGroup(id=entity.criteriaGroup.id, name = entity.criteriaGroup.name),
                evaluations = entity.evaluations.map {
                    EvaluationResponseDto.toDto(it)
                }
            )
        }
    }
}

data class CriteriaGroup(
    var id: Long?,
    var name: String
)