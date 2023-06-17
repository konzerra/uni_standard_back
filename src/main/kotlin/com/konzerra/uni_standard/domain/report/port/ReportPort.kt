package com.konzerra.uni_standard.domain.report.port

import com.konzerra.uni_standard.domain.report.Report
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReportPort {
    fun save(report: Report)
    fun deleteById(id: Long)
    fun findAll(): List<Report>
    fun findPaginatedByUser(pageable: Pageable, userId: Long): Page<Report>
    fun findById(id: Long): Report
}