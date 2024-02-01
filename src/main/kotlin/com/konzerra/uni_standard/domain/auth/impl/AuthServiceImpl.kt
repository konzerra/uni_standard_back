package com.konzerra.uni_standard.domain.auth.impl

import com.konzerra.uni_standard.domain.auth.AuthService
import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtRequestDto
import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtResponseDto
import com.konzerra.uni_standard.domain.auth.jwt.usecase.JwtUseCaseGenerateJwtToken
import com.konzerra.uni_standard.domain.user.role.port.RolePort
import com.konzerra.uni_standard.domain.user.User
import com.konzerra.uni_standard.domain.user.UserRoles
import com.konzerra.uni_standard.domain.user.dto.UserSaveDto
import com.konzerra.uni_standard.domain.user.port.UserPort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userPort: UserPort,
    private val rolePort: RolePort,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtUseCaseGenerateJwtToken: JwtUseCaseGenerateJwtToken
) : AuthService {
    override fun signup(userSaveDto: UserSaveDto) {
        userPort.save(
            User(
                name = userSaveDto.name,
                email = userSaveDto.email,
                password = passwordEncoder.encode(userSaveDto.password),
                roles = mutableSetOf(rolePort.findByName(UserRoles.USER))
            )
        )
    }

    override fun signin(jwtRequestDto: JwtRequestDto, lang: String): JwtResponseDto {
       return jwtUseCaseGenerateJwtToken.execute(jwtRequestDto, lang)
    }
}