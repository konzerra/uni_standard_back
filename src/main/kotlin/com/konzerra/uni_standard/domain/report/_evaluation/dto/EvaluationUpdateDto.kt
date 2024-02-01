package com.konzerra.uni_standard.domain.report._evaluation.dto

data class EvaluationUpdateDto(
    var id: Long,
    var criterionId: Long,
    var value: Double,
)