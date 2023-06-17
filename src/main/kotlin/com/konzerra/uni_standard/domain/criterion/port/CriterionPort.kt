package com.konzerra.uni_standard.domain.criterion.port

import com.konzerra.uni_standard.domain.criterion.Criterion

interface CriterionPort {
    fun save(criterion: Criterion)
    fun deleteById(id: Long)

    fun findById(id: Long): Criterion
}
