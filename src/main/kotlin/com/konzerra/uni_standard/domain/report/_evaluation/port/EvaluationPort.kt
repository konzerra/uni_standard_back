package com.konzerra.uni_standard.domain.report._evaluation.port

import com.konzerra.uni_standard.domain.report._evaluation.Evaluation

interface EvaluationPort {
    fun findById(id: Long): Evaluation
}