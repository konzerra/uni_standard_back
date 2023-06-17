package com.konzerra.uni_standard.security.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

@Component
class JwtUtil {
    companion object{
        private const val KEY = "KEY_MUST_BE_SECRET1"
        private const val TOKEN_VALIDITY = 3600 * 12
    }

    fun getUserNameFromToken(token: String): String {
        return getClaimFromToken(token) { obj: Claims -> obj.subject }
    }

    private fun <T> getClaimFromToken(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).body
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val userName = getUserNameFromToken(token)
        return userName == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    private fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token) { obj: Claims -> obj.expiration }
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS512, KEY)
            .compact()
    }
}