package com.konzerra.uni_standard.domain.standard.dto

import com.konzerra.uni_standard.domain.criterion.dto.CriterionSaveDto

data class StandardSaveDto(
    var name: String,
    var version: String,
    var description: String,
    var criteria: List<CriterionSaveDto>,
)