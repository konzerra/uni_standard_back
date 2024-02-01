package com.konzerra.uni_standard.domain.report.dto

import com.konzerra.uni_standard.domain.report._evalutation_group.dto.EvaluationGroupSaveDto
import com.konzerra.uni_standard.domain.university.dto.UniversitySaveDto

data class ReportSaveDto(
    var standardId: Long,
    var evaluationGroups: List<EvaluationGroupSaveDto>,
    var university: UniversitySaveDto
)