package com.konzerra.uni_standard.domain.user.role.port

import com.konzerra.uni_standard.domain.user.role.Role

interface RolePort {

    fun findByName(name:String): Role
}