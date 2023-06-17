package com.konzerra.uni_standard.domain.auth

import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtRequestDto
import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtResponseDto
import com.konzerra.uni_standard.domain.user.dto.UserSaveDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AuthController(
    private val authService: AuthService
) {



    @PostMapping(AuthApi.register)
    fun register(@RequestBody saveDto: UserSaveDto): ResponseEntity<*> {
        authService.register(saveDto)
        return ResponseEntity<Any>(HttpStatus.CREATED)
    }



    @PostMapping(AuthApi.generateJwtToken)
    fun generateToken(
        @RequestBody jwtRequestDto: JwtRequestDto,
        @RequestHeader("Accept-Language") lang:String
    ): ResponseEntity<JwtResponseDto> {
        return ResponseEntity(authService.generateToken(jwtRequestDto,lang), HttpStatus.OK)
    }
}