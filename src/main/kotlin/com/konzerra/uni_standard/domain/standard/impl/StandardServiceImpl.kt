package com.konzerra.uni_standard.domain.standard.impl

import com.konzerra.uni_standard.common.pagination.PaginationMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.criterion.Criterion
import com.konzerra.uni_standard.domain.standard.Standard
import com.konzerra.uni_standard.domain.standard.StandardService
import com.konzerra.uni_standard.domain.standard.StandardStatus
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

    override fun findAllPaginated(pageRequestDto: PageRequestDto, lang: String): Page<StandardResponseDto> {
        return standardPort.findPaginated(PaginationMapper.toPageable(pageRequestDto)).map {
            StandardResponseDto.toDto(it, lang)
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
                status = StandardStatus.OPEN,
                criteria = saveDto.criteria.map {
                    Criterion(
                        name = it.name,
                        description = it.description,
                        value = it.value
                    )
                }
            )
        )
    }

    override fun update(updateDto: StandardUpdateDto) {
        val standard = standardPort.findById(updateDto.id)
        standard.name = updateDto.name
        standard.version = updateDto.version
        standard.description = updateDto.description
        standard.status = updateDto.status
        standard.criteria = updateDto.criteria.map {
            Criterion(
                id = it.id,
                name = it.name,
                description = it.description,
                value = it.value
            )
        }
        standardPort.save(standard)
    }
}
