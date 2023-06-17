package com.konzerra.uni_standard.domain.report

import com.konzerra.uni_standard.AppLanguages
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.report.dto.ReportResponseDto
import com.konzerra.uni_standard.domain.report.dto.ReportSaveDto
import com.konzerra.uni_standard.domain.report.dto.ReportUpdateDto
import org.springframework.data.domain.Page

interface ReportService {
    fun findAll(lang: String = AppLanguages.DEFAULT): List<ReportResponseDto>
    fun findPaginatedByUser(pageRequestDto: PageRequestDto, userId: Long, lang: String = AppLanguages.DEFAULT): Page<ReportResponseDto>
    fun findById(id: Long, lang: String = AppLanguages.DEFAULT): ReportResponseDto
    fun deleteById(id: Long)
    fun save(saveDto: ReportSaveDto)
    fun update(updateDto: ReportUpdateDto)
}