package com.konzerra.uni_standard.domain.university.data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ProgramNumber(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    // 7 Количество реализуемых образовательных программах:
    var bachelor: Long = 0,
    var master: Long = 0,
    var specialty: Long = 0,
    var spo: Long = 0,
    var dpo: Long = 0
)
