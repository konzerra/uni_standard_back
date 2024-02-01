package com.konzerra.uni_standard.domain.user.role.port.impl

import com.konzerra.uni_standard.domain.user.role.Role
import com.konzerra.uni_standard.domain.user.role.RoleRepository
import com.konzerra.uni_standard.domain.user.role.port.RolePort
import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.exception.ResourceNotFoundException

@Port
class RolePortImpl(
    private val repository: RoleRepository
) : RolePort {
    override fun findByName(name: String): Role {
        try{
            return repository.findRoleByName(name)
        }catch(e:RuntimeException){
            throw ResourceNotFoundException(
                report = "Роль с названием $name не найдена",
            )
        }
    }
}