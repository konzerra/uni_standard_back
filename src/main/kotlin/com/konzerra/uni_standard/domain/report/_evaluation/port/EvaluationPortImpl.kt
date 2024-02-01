package com.konzerra.uni_standard.domain.report._evaluation.port

import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.domain.report._evaluation.Evaluation
import com.konzerra.uni_standard.domain.report._evaluation.EvaluationRepository
import com.konzerra.uni_standard.exception.ResourceNotFoundException

@Port
class EvaluationPortImpl(
    private val repository: EvaluationRepository
) : EvaluationPort {
    override fun findById(id: Long): Evaluation {
        return repository.findById(id).orElseThrow{
            ResourceNotFoundException("Evaluation with id: $id not found")
        }
    }
}