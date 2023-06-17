package com.konzerra.uni_standard.domain.user.impl

import com.konzerra.uni_standard.domain.user.dto.UserResponseDto
import com.konzerra.uni_standard.domain.user.dto.UserUpdateDto
import com.konzerra.uni_standard.domain.user.UserService
import com.konzerra.uni_standard.domain.user.port.UserPort
import com.konzerra.uni_standard.security.util.AuthUtil
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userPort: UserPort,
    private val authUtil: AuthUtil,
) : UserService {
    override fun findById(id: Long, lang: String): UserResponseDto {
        return UserResponseDto.toDto(userPort.findById(id), lang)
    }


    override fun update(updateDto: UserUpdateDto) {
        authUtil.isCurrentUserOrThrow(id = updateDto.id)
        val user = userPort.findById(updateDto.id)
        user.name = updateDto.name
        userPort.save(user)
    }
}