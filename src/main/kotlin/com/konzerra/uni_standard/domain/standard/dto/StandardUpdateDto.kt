package com.konzerra.uni_standard.domain.standard.dto

import com.konzerra.uni_standard.domain.criterion.dto.CriterionSaveDto
import com.konzerra.uni_standard.domain.criterion.dto.CriterionUpdateDto

data class StandardUpdateDto(
    var id: Long,
    var name: String,
    var version: String,
    var description: String,
    var status: String,
    var criteria: List<CriterionUpdateDto>,
)