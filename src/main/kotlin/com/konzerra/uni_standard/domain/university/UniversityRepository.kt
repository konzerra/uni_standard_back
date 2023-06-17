package com.konzerra.uni_standard.domain.university

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UniversityRepository: JpaRepository<University, Long> {
}