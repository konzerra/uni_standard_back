package com.konzerra.uni_standard.domain.report.dto

import com.konzerra.uni_standard.domain.report._evalutation_group.dto.EvaluationGroupUpdateDto
import com.konzerra.uni_standard.domain.university.dto.UniversityUpdateDto

data class ReportUpdateDto(
    var id: Long,
    var status: String,
    var evaluationGroups: List<EvaluationGroupUpdateDto>,
    var university: UniversityUpdateDto
)