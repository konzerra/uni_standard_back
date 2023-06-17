package com.konzerra.uni_standard.domain.report

import com.konzerra.uni_standard.domain.criterion_response.CriterionEvaluation
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
    var owner: User,

    @ManyToOne(cascade = [CascadeType.REFRESH], fetch = FetchType.LAZY)
    var standard: Standard,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var evaluations: List<CriterionEvaluation>,

    @ManyToOne(cascade = [CascadeType.REFRESH], fetch = FetchType.LAZY)
    var university: University
)