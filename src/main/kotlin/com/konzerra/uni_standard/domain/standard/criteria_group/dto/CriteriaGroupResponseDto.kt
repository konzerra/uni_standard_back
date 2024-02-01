package com.konzerra.uni_standard.domain.standard.criteria_group.dto

import com.konzerra.uni_standard.domain.standard.criteria_group.CriteriaGroup
import com.konzerra.uni_standard.domain.standard.criterion.dto.CriterionResponseDto
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

class CriteriaGroupResponseDto (
    var id: Long? = null,
    var name:String,
    var criteria: List<CriterionResponseDto>


    ){
    @Component
    companion object : Mapper<CriteriaGroup, CriteriaGroupResponseDto> {
        override fun toDto(entity: CriteriaGroup, lang: String): CriteriaGroupResponseDto {
            return CriteriaGroupResponseDto(
                id = entity.id,
                name = entity.name,
                criteria = entity.criteria.map {
                    CriterionResponseDto.toDto(it)
                }
            )
        }
    }
}
