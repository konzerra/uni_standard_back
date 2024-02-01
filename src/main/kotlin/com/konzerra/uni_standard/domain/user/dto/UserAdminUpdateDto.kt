package com.konzerra.uni_standard.domain.user.dto


class UserAdminUpdateDto(
    var id: Long,
    var name: String,
    var email: String,
    var password: String?,
    var roles: List<String>
)

