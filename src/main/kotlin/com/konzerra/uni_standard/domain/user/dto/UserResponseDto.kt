package com.konzerra.uni_standard.domain.user.dto


import com.konzerra.uni_standard.domain.user.role.dto.RoleResponseDto
import com.konzerra.uni_standard.domain.user.User
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component


class UserResponseDto(
    var id:Long? = null,
    var name:String,
    var email:String,
    var roles:List<RoleResponseDto>,
)
{

    @Component
    companion object: Mapper<User, UserResponseDto> {
        override fun toDto(entity: User, lang:String): UserResponseDto {
            return UserResponseDto(
                id = entity.id,
                email = entity.email,
                name = entity.name,
                roles = entity.roles.map{
                    RoleResponseDto(it.name)
                }
            )
        }
    }
}