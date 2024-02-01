package com.konzerra.uni_standard.domain.report

import com.konzerra.uni_standard.domain.report._evalutation_group.EvaluationGroup
import com.konzerra.uni_standard.domain.standard.Standard
import com.konzerra.uni_standard.domain.university.University
import com.konzerra.uni_standard.domain.user.User
import jakarta.persistence.*

@Entity
class Report(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var status:String,

    @ManyToOne(cascade = [CascadeType.REFRESH], fetch = FetchType.LAZY)
    var standard: Standard,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var evaluationGroups: List<EvaluationGroup> = emptyList(),

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var university: University,

    var average: Double = 0.0,
    var reserve: Double = 0.0
)