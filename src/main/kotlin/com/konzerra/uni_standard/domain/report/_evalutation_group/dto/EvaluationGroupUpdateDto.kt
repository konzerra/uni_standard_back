package com.konzerra.uni_standard.domain.report._evalutation_group.dto

import com.konzerra.uni_standard.domain.report._evaluation.dto.EvaluationUpdateDto
import jakarta.validation.Valid

data class EvaluationGroupUpdateDto(
    var id: Long,
    var criteriaGroupId: Long,
    @field:Valid
    var evaluations: List<EvaluationUpdateDto> = emptyList()
) {
}