package com.konzerra.uni_standard.domain.markdown


import jakarta.persistence.*


@Entity
class Markdown(

    @Id
    var id:String? = null,

    @Column(length = 200)
    var name:String = "no no data",
    @Column(length = 1000)
    var image: String = "",
    @Column(columnDefinition = "TEXT")
    var source:String = "no data",
    var priority: Int = 0,
)