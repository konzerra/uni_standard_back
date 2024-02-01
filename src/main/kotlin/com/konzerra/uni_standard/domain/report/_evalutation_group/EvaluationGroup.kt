package com.konzerra.uni_standard.domain.report._evalutation_group

import com.konzerra.uni_standard.domain.report._evaluation.Evaluation
import com.konzerra.uni_standard.domain.standard.criteria_group.CriteriaGroup
import jakarta.persistence.*

@Entity
class EvaluationGroup (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,

    @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.REFRESH])
    var criteriaGroup: CriteriaGroup,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var evaluations: List<Evaluation> = emptyList()
){

}