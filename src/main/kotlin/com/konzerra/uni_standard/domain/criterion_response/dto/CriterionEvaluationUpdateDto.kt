package com.konzerra.uni_standard.domain.criterion_response.dto

data class CriterionEvaluationUpdateDto(
    var id: Long,
    var criterionId: Long,
    var value: Double,
    var result: Double,
    var reserve: Double,
)