package com.konzerra.uni_standard.domain.report._evalutation_group

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EvaluationGroupRepository : JpaRepository<EvaluationGroup, Long> {
}