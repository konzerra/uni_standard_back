package com.konzerra.uni_standard.domain.auth.jwt.usecase.impl


import com.konzerra.uni_standard.security.util.JwtUtil
import com.konzerra.uni_standard.annotation.UseCase
import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtRequestDto
import com.konzerra.uni_standard.domain.auth.jwt.dto.JwtResponseDto
import com.konzerra.uni_standard.domain.auth.jwt.usecase.JwtUseCaseGenerateJwtToken
import com.konzerra.uni_standard.domain.user.User
import com.konzerra.uni_standard.domain.user.dto.UserResponseDto
import com.konzerra.uni_standard.domain.user.port.UserPort
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails

@UseCase
class JwtUseCaseGenerateJwtTokenImpl(
    private val jwtUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager,
    private val userPort: UserPort,
    private val userMapper: Mapper<User, UserResponseDto>,
) : JwtUseCaseGenerateJwtToken {

    override fun execute(jwtRequestDto: JwtRequestDto, lang:String): JwtResponseDto {
        val email: String = jwtRequestDto.email
        val userPassword: String = jwtRequestDto.password
        authenticate(email, userPassword)

        val userDetails: UserDetails = userPort.loadUserByUsername(email)
        val newGeneratedToken = jwtUtil.generateToken(userDetails)

        val userResponseDto: UserResponseDto = userMapper
            .toDto(userPort.findByEmail(email), lang)
        return JwtResponseDto(newGeneratedToken, userResponseDto)
    }


    private fun authenticate(userName: String, userPassword: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userName, userPassword))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}