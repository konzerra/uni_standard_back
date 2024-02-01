package com.konzerra.uni_standard.domain.standard.criterion.port

import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.domain.standard.criterion.Criterion
import com.konzerra.uni_standard.domain.standard.criterion.CriterionRepository
import com.konzerra.uni_standard.exception.ResourceNotFoundException

@Port
class CriterionPortImpl(
    private val repository: CriterionRepository
) : CriterionPort {
    override fun save(criterion: Criterion) {
        repository.save(criterion)
    }

    override fun deleteById(id: Long) {
        try {
            repository.deleteById(id)
        } catch (e: RuntimeException) {
            throw ResourceNotFoundException(
                report = "Criterion with id: $id not found"
            )
        }
    }

    override fun findById(id: Long): Criterion {
        return repository.findById(id).orElseThrow {
            throw ResourceNotFoundException(
                report = "Criterion with id: $id not found"
            )
        }
    }
}
