package com.konzerra.uni_standard.domain.standard

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StandardRepository: JpaRepository<Standard, Long> {

    fun findAllByStatus(status: String): List<Standard>
}