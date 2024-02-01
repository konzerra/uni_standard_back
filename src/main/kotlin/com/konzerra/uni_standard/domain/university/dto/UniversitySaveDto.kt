package com.konzerra.uni_standard.domain.university.dto

import com.konzerra.uni_standard.domain.university.data.AcademicStaffTraining
import com.konzerra.uni_standard.domain.university.data.ProgramNumber
import com.konzerra.uni_standard.domain.university.data.StudentNumber

data class UniversitySaveDto(
    var version: String = "",
    var name: String = "",
    var address: String = "",
    var yearFounded: Int = 0,
    var rectorName: String = "",
    var numOfEducationalUnits: Long = 0,
    var studentNumber: StudentNumber = StudentNumber(),
    var programNumber: ProgramNumber = ProgramNumber(),
    var academicStaffTraining: AcademicStaffTraining = AcademicStaffTraining(),
    var numOfFirstYearStudents: Long = 0,
    var numOfGraduates: Long = 0,
    var totalAreaOfEducationalFund: Long = 0,
)