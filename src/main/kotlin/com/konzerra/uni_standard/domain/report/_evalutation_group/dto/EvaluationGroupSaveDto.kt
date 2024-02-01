package com.konzerra.uni_standard.domain.report._evalutation_group.dto

import com.konzerra.uni_standard.domain.report._evaluation.dto.EvaluationSaveDto
import jakarta.validation.Valid

data class EvaluationGroupSaveDto(
    var criteriaGroupId: Long,
    @field:Valid
    var evaluations: List<EvaluationSaveDto> = emptyList()
) {
}