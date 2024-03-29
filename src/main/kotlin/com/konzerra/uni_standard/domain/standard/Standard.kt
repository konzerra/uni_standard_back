package com.konzerra.uni_standard.domain.standard

import com.konzerra.uni_standard.domain.standard.criteria_group.CriteriaGroup
import com.konzerra.uni_standard.domain.report.Report
import jakarta.persistence.*


@Entity
class Standard(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var name:String,
    var version: String,
    var description:String,
    var status: String,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var criteriaGroups: List<CriteriaGroup> = emptyList(),

    @OneToMany(mappedBy = "standard", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var reports: List<Report> = listOf()

)