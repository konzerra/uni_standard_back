package com.konzerra.uni_standard.domain.report.dto

import com.konzerra.uni_standard.domain.criterion_response.dto.CriterionEvaluationSaveDto

data class ReportSaveDto(
    var userId: Long,
    var standardId: Long,
    var evaluations: List<CriterionEvaluationSaveDto>,
    var universityId: Long
)