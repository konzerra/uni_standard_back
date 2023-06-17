package com.konzerra.uni_standard.domain.auth.jwt.dto

import com.konzerra.uni_standard.domain.user.dto.UserResponseDto


class JwtResponseDto(
    var jwtToken:String,
    var user: UserResponseDto
) {
}