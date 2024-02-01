package com.konzerra.uni_standard.domain.standard.criteria_group.dto

import com.konzerra.uni_standard.domain.standard.criterion.dto.CriterionUpdateDto
import jakarta.validation.Valid

data class CriteriaGroupUpdateDto(
    var id: Long?,
    var name: String,
    @field:Valid
    var criteria: List<CriterionUpdateDto> = emptyList()
) {

}