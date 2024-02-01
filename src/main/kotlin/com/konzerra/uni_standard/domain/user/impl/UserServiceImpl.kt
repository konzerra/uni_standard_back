package com.konzerra.uni_standard.domain.user.impl

import com.konzerra.uni_standard.common.pagination.PaginationMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.user.dto.UserResponseDto
import com.konzerra.uni_standard.domain.user.dto.UserUpdateDto
import com.konzerra.uni_standard.domain.user.UserService
import com.konzerra.uni_standard.domain.user.dto.UserAdminUpdateDto
import com.konzerra.uni_standard.domain.user.port.UserPort
import com.konzerra.uni_standard.domain.user.role.port.RolePort
import com.konzerra.uni_standard.security.util.AuthUtil
import org.springframework.data.domain.Page
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userPort: UserPort,
    private val authUtil: AuthUtil,
    private val rolePort: RolePort,
    private val passwordEncoder: BCryptPasswordEncoder,
) : UserService {
    override fun findById(id: Long, lang: String): UserResponseDto {
        return UserResponseDto.toDto(userPort.findById(id), lang)
    }

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun updateByAdmin(updateDto: UserAdminUpdateDto) {
        val user = userPort.findById(updateDto.id)
        user.name = updateDto.name
        user.email = updateDto.email
        updateDto.password?.let {
            user.password = passwordEncoder.encode(it)
        }
        user.roles.addAll(updateDto.roles.map {
            rolePort.findByName(it)
        })
        userPort.save(user)
    }

    override fun findPaginated(pageRequestDto: PageRequestDto): Page<UserResponseDto> {
        return userPort.findPaginated(PaginationMapper.toPageable(pageRequestDto)).map {
            UserResponseDto.toDto(it)
        }
    }

    override fun searchByEmail(pageRequestDto: PageRequestDto, email: String): Page<UserResponseDto> {
        return userPort.searchByEmail(PaginationMapper.toPageable(pageRequestDto), email).map {
            UserResponseDto.toDto(it)
        }
    }


    override fun update(updateDto: UserUpdateDto) {
        authUtil.isCurrentUserOrThrow(id = updateDto.id)
        val user = userPort.findById(updateDto.id)
        user.name = updateDto.name
        userPort.save(user)
    }
}