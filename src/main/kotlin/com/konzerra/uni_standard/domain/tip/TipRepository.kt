package com.konzerra.uni_standard.domain.tip

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TipRepository: JpaRepository<Tip, Long> {
}