package com.konzerra.uni_standard.domain.auth.jwt.usecase

import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtRequestDto
import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtResponseDto


interface JwtUseCaseGenerateJwtToken{
    fun execute(jwtRequestDto: JwtRequestDto, lang:String): JwtResponseDto
}