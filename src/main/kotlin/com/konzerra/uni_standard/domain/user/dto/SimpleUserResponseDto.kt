package com.konzerra.uni_standard.domain.user.dto

import com.konzerra.uni_standard.domain.user.User
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component

class SimpleUserResponseDto(
    var id:Long? = null,
    var name:String,
){
    @Component
    companion object: Mapper<User, SimpleUserResponseDto> {
        override fun toDto(entity: User, lang:String): SimpleUserResponseDto {
            return SimpleUserResponseDto(
                id = entity.id,
                name = entity.name
            )
        }
    }
}