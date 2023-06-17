package com.konzerra.uni_standard.domain.criterion_response

import com.konzerra.uni_standard.domain.criterion.Criterion
import jakarta.persistence.*

@Entity
class CriterionEvaluation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,

    @ManyToOne(cascade = [CascadeType.REFRESH], fetch = FetchType.LAZY)
    var criterion: Criterion,

    var value:Double,
    var result: Double = 0.0,
    var reserve: Double = 0.0,
) {
}