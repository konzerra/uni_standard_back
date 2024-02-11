package com.konzerra.uni_standard.domain.standard.impl

import com.konzerra.uni_standard.common.pagination.PaginationMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.report.ReportStatus
import com.konzerra.uni_standard.domain.standard.criteria_group.CriteriaGroup
import com.konzerra.uni_standard.domain.standard.criterion.Criterion
import com.konzerra.uni_standard.domain.standard.Standard
import com.konzerra.uni_standard.domain.standard.StandardService
import com.konzerra.uni_standard.domain.standard.StandardStatus
import com.konzerra.uni_standard.domain.standard.dto.StandardReportsResponseDto
import com.konzerra.uni_standard.domain.standard.dto.StandardResponseDto
import com.konzerra.uni_standard.domain.standard.dto.StandardSaveDto
import com.konzerra.uni_standard.domain.standard.dto.StandardUpdateDto
import com.konzerra.uni_standard.domain.standard.port.StandardPort
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class StandardServiceImpl(
    private val standardPort: StandardPort
) : StandardService {
    override fun findAllByStatus(status: String, lang: String): List<StandardResponseDto> {
        return standardPort.findAllByStatus(status).map {
            StandardResponseDto.toDto(it, lang)
        }
    }

    override fun findPaginated(pageRequestDto: PageRequestDto, lang: String): Page<StandardResponseDto> {
        return standardPort.findPaginated(PaginationMapper.toPageable(pageRequestDto)).map {
            StandardResponseDto.toDto(it, lang)
        }
    }

    override fun findPaginatedWithReports(
        pageRequestDto: PageRequestDto,
        lang: String
    ): Page<StandardReportsResponseDto> {
        return standardPort.findPaginated(PaginationMapper.toPageable(pageRequestDto)).map {
            StandardReportsResponseDto.toDto(it, lang)
        }
    }

    override fun findById(id: Long, lang: String): StandardResponseDto {
        return StandardResponseDto.toDto(standardPort.findById(id), lang)
    }

    override fun deleteById(id: Long) {
        standardPort.deleteById(id)
    }

    override fun save(saveDto: StandardSaveDto) {

        standardPort.save(
            Standard(
                name = saveDto.name,
                version = saveDto.version,
                description = saveDto.description,
                status = StandardStatus.REGISTERED,
                criteriaGroups = saveDto.criteriaGroups.map {
                    CriteriaGroup(
                        name = it.name,
                        criteria = it.criteria.map { criterionSaveDto ->
                            Criterion(
                                name = criterionSaveDto.name,
                                description = criterionSaveDto.description,
                                value = criterionSaveDto.value
                            )
                        }
                    )
                }
            )
        )
    }

    override fun update(updateDto: StandardUpdateDto) {
        val standard = standardPort.findById(updateDto.id)

        //TODO deal with different status and criteria update
        //Do not update criteria value if already published
        if(standard.status != StandardStatus.PUBLISHED){
            standard.criteriaGroups = updateDto.criteriaGroups.map {
                CriteriaGroup(
                    id = it.id,
                    name = it.name,
                    criteria = it.criteria.map { updateDto ->
                        Criterion(
                            id = updateDto.id,
                            name = updateDto.name,
                            description = updateDto.description,
                            value = updateDto.value
                        )
                    }
                )

            }
        }

        //Do not roll back from PUBLISHED status
        if(standard.status != StandardStatus.PUBLISHED){
            standard.status = updateDto.status
        }

        standard.name = updateDto.name
        standard.version = updateDto.version
        standard.description = updateDto.description


        standardPort.save(standard)
    }

    override fun findAllPublishedWithReports(): List<StandardReportsResponseDto> {
        return standardPort.findAllByStatus(StandardStatus.PUBLISHED).flatMap {
            val standardDto = StandardReportsResponseDto.toDto(it)
            val filteredReports = standardDto.reports.filter { report ->
                report.status == ReportStatus.PUBLISHED
            }
            if (filteredReports.isNotEmpty()) {
                listOf(standardDto.copy(reports = filteredReports))
            } else {
                emptyList()
            }
        }.sortedByDescending {
            it.id
        }
    }
}
