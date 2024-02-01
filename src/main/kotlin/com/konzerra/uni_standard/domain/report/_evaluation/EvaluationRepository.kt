package com.konzerra.uni_standard.domain.report._evaluation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EvaluationRepository : JpaRepository<Evaluation, Long> {
}