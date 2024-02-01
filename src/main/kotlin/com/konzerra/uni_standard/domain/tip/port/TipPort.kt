package com.konzerra.uni_standard.domain.tip.port

import com.konzerra.uni_standard.domain.tip.Tip
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TipPort {

    fun save(tip: Tip)

    fun deleteById(id: Long)

    fun findAllPaginated(pageable: Pageable): Page<Tip>

    fun findAll():List<Tip>

    fun findById(id:Long): Tip
}