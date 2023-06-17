package com.konzerra.uni_standard.domain.university.dto

import com.konzerra.uni_standard.domain.university.University
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

data class UniversityResponseDto(
    var id: Long?,
){
    @Component
    companion object : Mapper<University, UniversityResponseDto>{
        override fun toDto(entity: University, lang: String): UniversityResponseDto {
            return UniversityResponseDto(
                id= entity.id
            )
        }

    }
}
