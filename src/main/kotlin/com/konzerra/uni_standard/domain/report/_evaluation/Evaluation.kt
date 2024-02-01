package com.konzerra.uni_standard.domain.report._evaluation

import com.konzerra.uni_standard.domain.standard.criterion.Criterion
import jakarta.persistence.*

@Entity
class Evaluation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,

    @ManyToOne(cascade = [CascadeType.REFRESH], fetch = FetchType.LAZY)
    var criterion: Criterion,

    var value:Double = 0.0,

    //calculated values
    var result: Double = 0.0,
    var reserve: Double = 0.0,
) {
}