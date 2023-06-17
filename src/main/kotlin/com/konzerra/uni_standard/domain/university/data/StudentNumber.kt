package com.konzerra.uni_standard.domain.university.data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class StudentNumber(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    // 6 Контингент обучающихся по программам:
    var bachelor: Long = 0,
    var master: Long = 0,
    var specialty: Long = 0,
    var spo: Long = 0,
    var postgraduate: Long = 0,
    var doctorate: Long = 0,
    var seeker: Long = 0,
    var phd: Long = 0
)