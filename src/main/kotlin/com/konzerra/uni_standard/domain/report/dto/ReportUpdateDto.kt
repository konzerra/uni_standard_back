package com.konzerra.uni_standard.domain.report.dto

import com.konzerra.uni_standard.domain.criterion_response.dto.CriterionEvaluationUpdateDto

data class ReportUpdateDto(
    var id: Long,
    var status: String,
    var evaluations: List<CriterionEvaluationUpdateDto>
)