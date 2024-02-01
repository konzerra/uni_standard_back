package com.konzerra.uni_standard.domain.standard.criterion

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CriterionRepository: JpaRepository<Criterion, Long> {
}