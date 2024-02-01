package com.konzerra.uni_standard.domain.user.port

import com.konzerra.uni_standard.domain.user.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetailsService


interface UserPort: UserDetailsService {

    fun deleteById(userId: Long)

    fun save(user: User)

    fun findAll():List<User>

    fun findById(userId: Long):User

    fun findByEmail(email: String):User

    fun findPaginated(pageable: Pageable): Page<User>

    fun searchByEmail(pageable: Pageable, email: String): Page<User>
}