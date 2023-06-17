package com.konzerra.uni_standard.domain.user.role

import com.konzerra.uni_standard.domain.user.role.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository:JpaRepository<Role,Long> {
    fun findRoleByName(name: String): Role
}
