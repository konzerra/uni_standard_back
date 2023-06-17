package com.konzerra.uni_standard.domain.criterion_response.dto

data class CriterionEvaluationSaveDto(
    var criterionId: Long,
    var value: Double,
    var result: Double,
    var reserve: Double,
)