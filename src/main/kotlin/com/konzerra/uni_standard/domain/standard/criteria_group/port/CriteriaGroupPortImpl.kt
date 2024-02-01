package com.konzerra.uni_standard.domain.standard.criteria_group.port

import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.domain.standard.criteria_group.CriteriaGroup
import com.konzerra.uni_standard.domain.standard.criteria_group.CriteriaGroupRepository
import com.konzerra.uni_standard.exception.ResourceNotFoundException

@Port
class CriteriaGroupPortImpl(
    private val repository: CriteriaGroupRepository
) : CriteriaGroupPort {
    override fun findById(id: Long): CriteriaGroup {
        return repository.findById(id).orElseThrow {
            throw ResourceNotFoundException(
                report = "CriteriaGroup with id: $id not found"
            )
        }
    }
}