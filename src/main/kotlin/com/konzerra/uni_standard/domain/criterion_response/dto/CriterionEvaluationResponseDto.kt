package com.konzerra.uni_standard.domain.criterion_response.dto

import com.konzerra.uni_standard.domain.criterion.dto.CriterionResponseDto
import com.konzerra.uni_standard.domain.criterion_response.CriterionEvaluation
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

data class CriterionEvaluationResponseDto(
    var id: Long? = null,
    var criterion: CriterionResponseDto,
    var value: Double,
    var result: Double,
    var reserve: Double,
) {
    @Component
    companion object : Mapper<CriterionEvaluation, CriterionEvaluationResponseDto> {
        override fun toDto(entity: CriterionEvaluation, lang: String): CriterionEvaluationResponseDto {
            return CriterionEvaluationResponseDto(
                id = entity.id,
                criterion = CriterionResponseDto.toDto(entity.criterion, lang),
                value = entity.value,
                result = entity.result,
                reserve = entity.reserve
            )
        }
    }
}