package com.konzerra.uni_standard.domain.standard.criteria_group

import com.konzerra.uni_standard.domain.standard.criterion.Criterion
import jakarta.persistence.*


@Entity
class CriteriaGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var name:String,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var criteria: List<Criterion> = emptyList()

) {
}