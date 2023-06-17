package com.konzerra.uni_standard.domain.user

import com.konzerra.uni_standard.domain.user.dto.UserResponseDto
import com.konzerra.uni_standard.domain.user.dto.UserUpdateDto

interface UserService {

    fun findById(id: Long, lang: String): UserResponseDto

    fun update(updateDto: UserUpdateDto)
}