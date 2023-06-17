package com.konzerra.uni_standard.domain.standard.port.impl

import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.domain.standard.Standard
import com.konzerra.uni_standard.domain.standard.StandardRepository
import com.konzerra.uni_standard.domain.standard.port.StandardPort
import com.konzerra.uni_standard.exception.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Port
class StandardPortImpl(
    private val repository: StandardRepository
) : StandardPort {
    override fun save(standard: Standard) {
        repository.save(standard)
    }

    override fun deleteById(id: Long) {
        try {
            repository.deleteById(id)
        } catch (e: RuntimeException) {
            throw ResourceNotFoundException(
                report = "Стандарт с id: $id не найден"
            )
        }
    }

    override fun findAllByStatus(status: String): List<Standard> {
        return repository.findAllByStatus(status)
    }

    override fun findPaginated(pageable: Pageable): Page<Standard> {
        return repository.findAll(pageable)
    }

    override fun findById(id: Long): Standard {
        return repository.findById(id).orElseThrow {
            throw ResourceNotFoundException(
                report = "Стандарт с id: $id не найден"
            )
        }
    }
}
