package com.konzerra.uni_standard.domain.report.port

import com.konzerra.uni_standard.domain.report.Report

interface ReportPort {
    fun save(report: Report)
    fun deleteById(id: Long)
    fun findAll(): List<Report>
    fun findById(id: Long): Report
}