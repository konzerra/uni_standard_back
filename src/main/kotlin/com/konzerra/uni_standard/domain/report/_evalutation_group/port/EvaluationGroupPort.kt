package com.konzerra.uni_standard.domain.report._evalutation_group.port

import com.konzerra.uni_standard.domain.report._evalutation_group.EvaluationGroup

interface EvaluationGroupPort {
    fun findById(id: Long): EvaluationGroup
}