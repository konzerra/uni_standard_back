package com.konzerra.uni_standard.domain.user.port

import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.domain.user.role.Role
import com.konzerra.uni_standard.domain.user.User
import com.konzerra.uni_standard.domain.user.UserRepository
import com.konzerra.uni_standard.domain.user.dto.UserResponseDto
import com.konzerra.uni_standard.exception.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Port
class UserPortImpl(
    private val repository: UserRepository
) : UserPort {
    override fun deleteById(userId: Long) {
        try{
            repository.deleteById(userId)
        }catch(e:RuntimeException){
            throw ResourceNotFoundException(
                report = "Пользователь с id: $userId не найден",
            )
        }
    }

    override fun save(user: User) {
        repository.save(user)
    }

    override fun findAll(): List<User> {
        return repository.findAll()
    }

    override fun findById(userId: Long): User {
        return repository.findById(userId).orElseThrow {
           ResourceNotFoundException(
               report = "Пользователь с id: $userId не найден",
            )
        }
    }

    override fun findByEmail(email: String): User {
        try{
            return repository.findByEmail(email)
        }catch(e:RuntimeException){
            throw ResourceNotFoundException(
                report = "Пользователь с почтой: $email не найден",
            )
        }
    }

    override fun findPaginated(pageable: Pageable): Page<User> {
        return repository.findAll(pageable)
    }

    override fun searchByEmail(pageable: Pageable, email: String): Page<User> {
        return repository.findUsersByEmailContains(pageable, email)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = findByEmail(username)

        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password,
            mapRolesToAuthorities(user.roles.toList())
        )
    }
    private fun mapRolesToAuthorities(roles: List<Role>): Collection<GrantedAuthority?> {
        val authorities: List<SimpleGrantedAuthority> = roles.map {
            SimpleGrantedAuthority("ROLE_${it.name}")
        }
        return authorities
    }
}