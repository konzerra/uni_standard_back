package com.konzerra.uni_standard.domain.university.port

import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.domain.university.University
import com.konzerra.uni_standard.domain.university.UniversityRepository
import com.konzerra.uni_standard.exception.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Port
class UniversityPortImpl(
    private val repository: UniversityRepository
) : UniversityPort {
    override fun save(university: University): University {
        return repository.save(university)
    }

    override fun deleteById(id: Long) {
        try {
            repository.deleteById(id)
        } catch (e: RuntimeException) {
            throw ResourceNotFoundException(
                report = "Университет с id: $id не найден"
            )
        }
    }

    override fun findAll(): List<University> {
        return repository.findAll()
    }

    override fun findAllPaginated(pageable: Pageable): Page<University> {
        return repository.findAll(pageable)
    }

    override fun findById(id: Long): University {
        return repository.findById(id).orElseThrow {
            throw ResourceNotFoundException(
                report = "Университет с id: $id не найден"
            )
        }
    }
}
