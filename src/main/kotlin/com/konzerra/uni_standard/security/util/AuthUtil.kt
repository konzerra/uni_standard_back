package com.konzerra.uni_standard.security.util

interface AuthUtil {
    fun isCurrentUser(email: String):Boolean

    fun isCurrentUser(id: Long):Boolean

    fun isCurrentUserOrThrow(id: Long)

    fun isCurrentUserOrThrow(email: String)

    fun isRoleValid(email: String, role:String): Boolean

    fun isRoleValidOrThrow(email: String, role:String)
}