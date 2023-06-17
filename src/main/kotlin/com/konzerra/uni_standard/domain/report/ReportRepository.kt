package com.konzerra.uni_standard.domain.report

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReportRepository: JpaRepository<Report, Long> {
    fun findAllByOwnerId(pageable: Pageable, ownerId: Long):Page<Report>
}