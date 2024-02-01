package com.konzerra.uni_standard.domain.standard.criterion

import jakarta.persistence.*

@Entity
class Criterion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var name:String,
    var description: String,
    var value: Double,
) {
}