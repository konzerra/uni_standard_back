package com.konzerra.uni_standard.domain.criterion.dto

import com.konzerra.uni_standard.domain.criterion.Criterion
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

class CriterionResponseDto(
    var id: Long? = null,
    var name:String,
    var description: String,
    var value: Double,


){
    @Component
    companion object : Mapper<Criterion, CriterionResponseDto> {
        override fun toDto(entity: Criterion, lang: String): CriterionResponseDto {
            return CriterionResponseDto(
                id = entity.id,
                name = entity.name,
                description = entity.description,
                value = entity.value
            )
        }
    }
}
