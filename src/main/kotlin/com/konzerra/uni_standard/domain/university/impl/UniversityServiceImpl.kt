package com.konzerra.uni_standard.domain.university.impl

import com.konzerra.uni_standard.common.pagination.PaginationMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.university.University
import com.konzerra.uni_standard.domain.university.UniversityService
import com.konzerra.uni_standard.domain.university.dto.UniversityResponseDto
import com.konzerra.uni_standard.domain.university.dto.UniversitySaveDto
import com.konzerra.uni_standard.domain.university.dto.UniversityUpdateDto
import com.konzerra.uni_standard.domain.university.port.UniversityPort
import com.konzerra.uni_standard.domain.user.port.UserPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UniversityServiceImpl(
    private val universityPort: UniversityPort,
    private val userPort: UserPort
) : UniversityService {

    override fun save(universityDto: UniversitySaveDto){
        val university = University(
            manager = userPort.findById(universityDto.userId),
        ).apply {
            version = universityDto.version
            name = universityDto.name
            address = universityDto.address
            yearFounded = universityDto.yearFounded
            rectorName = universityDto.rectorName
            numOfEducationalUnits = universityDto.numOfEducationalUnits
            studentNumber = universityDto.studentNumber
            programNumber = universityDto.programNumber
            academicStaffTraining = universityDto.academicStaffTraining
            numOfFirstYearStudents = universityDto.numOfFirstYearStudents
            numOfGraduates = universityDto.numOfGraduates
            totalAreaOfEducationalFund = universityDto.totalAreaOfEducationalFund
        }
        universityPort.save(university)
    }

    override fun update(universityDto: UniversityUpdateDto) {
        val university = universityPort.findById(universityDto.id)
        university.apply {
            version = universityDto.version
            name = universityDto.name
            address = universityDto.address
            yearFounded = universityDto.yearFounded
            rectorName = universityDto.rectorName
            numOfEducationalUnits = universityDto.numOfEducationalUnits
            studentNumber = universityDto.studentNumber
            programNumber = universityDto.programNumber
            academicStaffTraining = universityDto.academicStaffTraining
            numOfFirstYearStudents = universityDto.numOfFirstYearStudents
            numOfGraduates = universityDto.numOfGraduates
            totalAreaOfEducationalFund = universityDto.totalAreaOfEducationalFund
        }

        universityPort.save(university)
    }

    override fun deleteById(id: Long) {
        universityPort.deleteById(id)
    }

    override fun findAll(): List<UniversityResponseDto> {
        return universityPort.findAll().map { UniversityResponseDto.toDto(it) }
    }

    override fun findPaginated(pageRequestDto: PageRequestDto): Page<UniversityResponseDto> {
        return universityPort.findAllPaginated(
            PaginationMapper.toPageable(pageRequestDto)
        ).map { UniversityResponseDto.toDto(it) }
    }

    override fun findById(id: Long): UniversityResponseDto {
        val university = universityPort.findById(id)
        return UniversityResponseDto.toDto(university)
    }
}
