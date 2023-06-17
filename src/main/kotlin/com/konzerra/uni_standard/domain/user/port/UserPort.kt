package com.konzerra.uni_standard.domain.user.port

import com.konzerra.uni_standard.domain.user.User
import org.springframework.security.core.userdetails.UserDetailsService


interface UserPort: UserDetailsService {

    fun deleteById(userId: Long)

    fun save(user: User)

    fun findAll():List<User>

    fun findById(userId: Long):User

    fun findByEmail(email: String):User
}