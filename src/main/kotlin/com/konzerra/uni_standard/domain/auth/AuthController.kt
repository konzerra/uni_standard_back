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



    @PostMapping(AuthApi.signup)
    fun register(@RequestBody saveDto: UserSaveDto): ResponseEntity<*> {
        authService.signup(saveDto)
        return ResponseEntity<Any>(HttpStatus.CREATED)
    }



    @PostMapping(AuthApi.signin)
    fun generateToken(
        @RequestBody jwtRequestDto: JwtRequestDto,
        @RequestHeader("Accept-Language") lang:String
    ): ResponseEntity<JwtResponseDto> {
        return ResponseEntity(authService.signin(jwtRequestDto,lang), HttpStatus.OK)
    }
}