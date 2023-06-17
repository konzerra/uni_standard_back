package com.konzerra.uni_standard.common.pagination

import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

object PaginationMapper {
    fun toPageable(pageRequestDto: PageRequestDto): Pageable {
        val sort = Sort.by(pageRequestDto.sort.map { sortDto ->
            Sort.Order(Sort.Direction.fromString(sortDto.direction), sortDto.property)
        })

        return PageRequest.of(pageRequestDto.page, pageRequestDto.size, sort)
    }

}