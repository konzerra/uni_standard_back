package com.konzerra.uni_standard.security

import com.konzerra.uni_standard.domain.user.port.UserPort
import com.konzerra.uni_standard.security.util.JwtUtil
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * Authentication token is sent via header
 */
@Component
class JwtTokenFilter(
    private val jwtUtil: JwtUtil,
    private val userPort: UserPort,
) : OncePerRequestFilter() {


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")
        var jwtToken: String? = null
        var userName: String? = null
        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7)
            try {
                userName = jwtUtil.getUserNameFromToken(jwtToken)
            } catch (illegalArgumentException: IllegalArgumentException) {
                //
            } catch (expiredJwtException: ExpiredJwtException) {
                //
            }
        } else {
            //
        }
        if (userName != null && SecurityContextHolder.getContext().authentication == null && jwtToken!=null) {
            val userDetails: UserDetails = userPort.loadUserByUsername(userName)
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}