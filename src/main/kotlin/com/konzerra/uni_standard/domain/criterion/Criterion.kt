package com.konzerra.uni_standard.domain.criterion

import com.konzerra.uni_standard.domain.criterion_response.CriterionEvaluation
import jakarta.persistence.*

@Entity
class Criterion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var name:String,
    var description: String,
    var value: Double,

    @OneToMany(mappedBy = "criterion", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var evaluations: List<CriterionEvaluation> = emptyList()
) {
}