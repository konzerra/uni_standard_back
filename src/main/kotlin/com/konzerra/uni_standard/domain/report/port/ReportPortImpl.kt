package com.konzerra.uni_standard.domain.report.port

import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.domain.report.Report
import com.konzerra.uni_standard.domain.report.ReportRepository
import com.konzerra.uni_standard.exception.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Port
class ReportPortImpl(
    private val repository: ReportRepository
) : ReportPort {
    override fun save(report: Report) {
        repository.save(report)
    }

    override fun deleteById(id: Long) {
        try {
            repository.deleteById(id)
        } catch (e: RuntimeException) {
            throw ResourceNotFoundException(
                report = "Report with id: $id not found"
            )
        }
    }

    override fun findAll(): List<Report> {
        return repository.findAll()
    }


    override fun findById(id: Long): Report {
        return repository.findById(id).orElseThrow {
            throw ResourceNotFoundException(
                report = "Report with id: $id not found"
            )
        }
    }
}
