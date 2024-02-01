package com.konzerra.uni_standard.domain.standard.dto

import com.konzerra.uni_standard.domain.standard.criteria_group.dto.CriteriaGroupUpdateDto
import jakarta.validation.Valid

data class StandardUpdateDto(
    var id: Long,
    var name: String,
    var version: String,
    var description: String,
    var status: String,
    @field:Valid
    var criteriaGroups: List<CriteriaGroupUpdateDto>,
)