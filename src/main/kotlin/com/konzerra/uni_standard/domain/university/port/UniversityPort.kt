package com.konzerra.uni_standard.domain.university.port

import com.konzerra.uni_standard.domain.university.University
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UniversityPort {
    fun save(university: University): University
    fun deleteById(id: Long)
    fun findAll(): List<University>
    fun findAllPaginated(pageable: Pageable): Page<University>
    fun findById(id: Long): University
}
