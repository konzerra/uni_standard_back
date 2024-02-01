package com.konzerra.uni_standard.domain.tip

import jakarta.persistence.*

@Entity
class Tip(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(length = 2000)
    var question: String = "no translation",
    @Column(columnDefinition = "TEXT")
    var answer: String = "no translation"
){
}