package com.konzerra.uni_standard.domain.report

import com.konzerra.uni_standard.domain.report.dto.ReportResponseDto
import com.konzerra.uni_standard.domain.report.dto.ReportSaveDto
import com.konzerra.uni_standard.domain.report.dto.ReportUpdateDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class ReportController(
    private val reportService: ReportService
) {

    @PreAuthorize("hasRole('Admin')")
    @PutMapping(ReportApi.update)
    fun update(@RequestBody updateDto: ReportUpdateDto): ResponseEntity<*> {
        reportService.update(updateDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(ReportApi.save)
    fun save(@RequestBody saveDto: ReportSaveDto): ResponseEntity<*> {
        reportService.save(saveDto)
        return ResponseEntity<Any>(HttpStatus.CREATED)
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping(ReportApi.deleteById)
    fun deleteById(@PathVariable id: Long): ResponseEntity<*> {
        reportService.deleteById(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @GetMapping(ReportApi.findById)
    fun findById(
        @PathVariable id: Long,
        @RequestHeader("Accept-Language") lang: String
    ): ResponseEntity<ReportResponseDto> {

        return ResponseEntity(reportService.findById(id, lang), HttpStatus.OK)
    }

    @GetMapping(ReportApi.findAll)
    fun findAll(@RequestHeader("Accept-Language") lang: String): ResponseEntity<List<ReportResponseDto>> {
        return ResponseEntity(reportService.findAll(lang), HttpStatus.OK)
    }

}
