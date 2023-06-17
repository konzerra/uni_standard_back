package com.konzerra.uni_standard.domain.university.data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class AcademicStaffTraining(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    // 8. Сведения о подготовке научно-педагогических кадров:
    var postgraduate: Long = 0,
    var doctoral: Long = 0,
    var phd: Long = 0
)