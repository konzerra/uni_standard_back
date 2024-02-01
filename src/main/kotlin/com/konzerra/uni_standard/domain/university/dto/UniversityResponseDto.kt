package com.konzerra.uni_standard.domain.university.dto

import com.konzerra.uni_standard.domain.university.University
import com.konzerra.uni_standard.domain.university.data.AcademicStaffTraining
import com.konzerra.uni_standard.domain.university.data.ProgramNumber
import com.konzerra.uni_standard.domain.university.data.StudentNumber
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

data class UniversityResponseDto(
    var id: Long?,
    var version: String,
    var name: String,
    var address: String,
    var yearFounded: Int,
    var rectorName: String,
    var numOfEducationalUnits: Long,
    var studentNumber: StudentNumber,
    var programNumber: ProgramNumber,
    var academicStaffTraining: AcademicStaffTraining,
    var numOfFirstYearStudents: Long,
    var numOfGraduates: Long,
    var totalAreaOfEducationalFund: Long,
) {
    @Component
    companion object : Mapper<University, UniversityResponseDto> {
        override fun toDto(entity: University, lang: String): UniversityResponseDto {
            return UniversityResponseDto(
                id = entity.id,
                version = entity.version,
                name = entity.name,
                address = entity.address,
                yearFounded = entity.yearFounded,
                rectorName = entity.rectorName,
                numOfEducationalUnits = entity.numOfEducationalUnits,
                studentNumber = entity.studentNumber,
                programNumber = entity.programNumber,
                academicStaffTraining = entity.academicStaffTraining,
                numOfFirstYearStudents = entity.numOfFirstYearStudents,
                numOfGraduates = entity.numOfGraduates,
                totalAreaOfEducationalFund = entity.totalAreaOfEducationalFund,
            )
        }
    }
}
