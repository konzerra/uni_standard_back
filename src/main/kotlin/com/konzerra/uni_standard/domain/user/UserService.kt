package com.konzerra.uni_standard.domain.user

import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.user.dto.UserAdminUpdateDto
import com.konzerra.uni_standard.domain.user.dto.UserResponseDto
import com.konzerra.uni_standard.domain.user.dto.UserSaveDto
import com.konzerra.uni_standard.domain.user.dto.UserUpdateDto
import org.springframework.data.domain.Page

interface UserService {

    fun findById(id: Long, lang: String): UserResponseDto

    /**
     * Admin only function to register new user
     */


    fun deleteById(id: Long)

    fun updateByAdmin(updateDto: UserAdminUpdateDto)

    fun findPaginated(pageRequestDto: PageRequestDto): Page<UserResponseDto>

    fun searchByEmail(pageRequestDto: PageRequestDto, email: String):Page<UserResponseDto>

    fun update(updateDto: UserUpdateDto)
}