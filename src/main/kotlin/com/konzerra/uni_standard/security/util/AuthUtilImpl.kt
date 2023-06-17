package com.konzerra.uni_standard.security.util

import com.konzerra.uni_standard.domain.user.port.UserPort
import com.konzerra.uni_standard.exception.UnauthorizedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component


@Component
class AuthUtilImpl(
    private val userPort: UserPort
): AuthUtil {
    override fun isCurrentUser(email: String): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.isAuthenticated) {
            val userDetails = authentication.principal as UserDetails
            return userDetails.username == email
        }
        return false
    }

    override fun isCurrentUser(id: Long): Boolean {
        val user = userPort.findById(id)
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.isAuthenticated) {
            val userDetails = authentication.principal as UserDetails
            return userDetails.username == user.email
        }
        return false
    }

    override fun isCurrentUserOrThrow(id: Long) {
        val user = userPort.findById(id)
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.isAuthenticated) {
            val userDetails = authentication.principal as UserDetails
            if (userDetails.username == user.email){
                return
            }
        }
        throw UnauthorizedException("access_to_another_user's_data_denied")
    }

    override fun isCurrentUserOrThrow(email: String){
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.isAuthenticated) {
            val userDetails = authentication.principal as UserDetails
            if (userDetails.username == email){
                return
            }
        }
        throw UnauthorizedException("access_to_another_user's_data_denied")
    }

    override fun isRoleValid(email: String, role: String): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.isAuthenticated) {
            val userDetails = authentication.principal as UserDetails
            val fullRole = "ROLE_$role"
            if (userDetails.username == email) {
                return userDetails.authorities.any { it.authority == fullRole }
            }
        }
        return false
    }


    override fun isRoleValidOrThrow(email: String, role: String) {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.isAuthenticated) {
            val userDetails = authentication.principal as UserDetails
            val fullRole = "ROLE_$role"
            if (userDetails.username == email && userDetails.authorities.any { it.authority == fullRole }) {
                return
            }
        }
        throw UnauthorizedException("unauthorized")
    }

}