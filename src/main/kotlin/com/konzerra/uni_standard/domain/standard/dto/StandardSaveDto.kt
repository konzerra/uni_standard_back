package com.konzerra.uni_standard.domain.standard.dto

import com.konzerra.uni_standard.domain.standard.criteria_group.dto.CriteriaGroupSaveDto
import jakarta.validation.Valid

data class StandardSaveDto(
    var name: String,
    var version: String,
    var description: String,
    @field:Valid
    var criteriaGroups: List<CriteriaGroupSaveDto>,
)