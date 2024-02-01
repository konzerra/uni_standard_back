package com.konzerra.uni_standard.domain.standard.criteria_group.port

import com.konzerra.uni_standard.domain.standard.criteria_group.CriteriaGroup

interface CriteriaGroupPort {
    fun findById(id: Long): CriteriaGroup
}