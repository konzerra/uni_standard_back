package com.konzerra.uni_standard.domain.report._evalutation_group.port

import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.domain.report._evalutation_group.EvaluationGroup
import com.konzerra.uni_standard.domain.report._evalutation_group.EvaluationGroupRepository
import com.konzerra.uni_standard.exception.ResourceNotFoundException

@Port
class EvaluationGroupPortImpl(
    private val repository: EvaluationGroupRepository
) : EvaluationGroupPort {
    override fun findById(id: Long): EvaluationGroup {
        return repository.findById(id).orElseThrow{
            ResourceNotFoundException("Evaluation with id: $id not found")
        }
    }
}