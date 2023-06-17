package com.konzerra.uni_standard.domain.auth

import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtRequestDto
import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtResponseDto
import com.konzerra.uni_standard.domain.user.dto.UserSaveDto

interface AuthService {

    fun register(userSaveDto: UserSaveDto)

    fun generateToken(jwtRequestDto: JwtRequestDto, lang: String): JwtResponseDto


}