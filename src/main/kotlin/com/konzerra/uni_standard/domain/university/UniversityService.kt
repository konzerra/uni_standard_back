package com.konzerra.uni_standard.domain.university

import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.university.dto.UniversityResponseDto
import com.konzerra.uni_standard.domain.university.dto.UniversitySaveDto
import com.konzerra.uni_standard.domain.university.dto.UniversityUpdateDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UniversityService {
    fun save(universityDto: UniversitySaveDto): UniversityResponseDto
    fun update(universityDto: UniversityUpdateDto): UniversityResponseDto
    fun deleteById(id: Long)
    fun findAll(): List<UniversityResponseDto>
    fun findPaginated(pageRequestDto: PageRequestDto): Page<UniversityResponseDto>
    fun findById(id: Long): UniversityResponseDto
}
