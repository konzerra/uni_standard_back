package com.konzerra.uni_standard.domain.criterion.dto

data class CriterionUpdateDto(
    var id: Long,
    var name: String,
    var description: String,
    var value: Double
)
