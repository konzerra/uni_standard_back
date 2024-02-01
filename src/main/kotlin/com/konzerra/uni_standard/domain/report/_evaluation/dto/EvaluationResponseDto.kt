package com.konzerra.uni_standard.domain.report._evaluation.dto

import com.konzerra.uni_standard.domain.standard.criterion.dto.CriterionResponseDto
import com.konzerra.uni_standard.domain.report._evaluation.Evaluation
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

data class EvaluationResponseDto(
    var id: Long? = null,
    var criterion: CriterionResponseDto,
    var value: Double,
    var result: Double,
    var reserve: Double,
) {
    @Component
    companion object : Mapper<Evaluation, EvaluationResponseDto> {
        override fun toDto(entity: Evaluation, lang: String): EvaluationResponseDto {
            return EvaluationResponseDto(
                id = entity.id,
                criterion = CriterionResponseDto.toDto(entity.criterion, lang),
                value = entity.value,
                result = entity.result,
                reserve = entity.reserve
            )
        }
    }
}