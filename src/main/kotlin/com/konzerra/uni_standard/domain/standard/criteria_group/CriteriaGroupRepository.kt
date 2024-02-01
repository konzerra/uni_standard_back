package com.konzerra.uni_standard.domain.standard.criteria_group

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CriteriaGroupRepository : JpaRepository<CriteriaGroup, Long> {
}