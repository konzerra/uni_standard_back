package com.konzerra.uni_standard.domain.standard.criteria_group.dto

import com.konzerra.uni_standard.domain.standard.criterion.dto.CriterionSaveDto
import jakarta.validation.Valid

data class CriteriaGroupSaveDto(
    var name:String,
    @field:Valid
    var criteria: List<CriterionSaveDto> = emptyList()
) {

}