package com.konzerra.uni_standard.domain.standard.criterion.dto

import jakarta.validation.constraints.DecimalMin

data class CriterionSaveDto(
    var name:String,
    var description: String,
    @field:DecimalMin(value = "0.01", message = "значение не критерри не может быть меньше или равно нулю")
    var value: Double,
)
