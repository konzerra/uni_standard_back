package com.konzerra.uni_standard.domain.user

import com.konzerra.uni_standard.ApiPath


class UserApi {
    companion object{
        private const val basePath = "/user"
        const val protectedPath = "${ApiPath.protectedPath}$basePath"
        const val publicPath = "${ApiPath.publicPath}$basePath"

        const val findById = "$protectedPath/{id}"

        const val update = protectedPath
        const val deleteById = "$protectedPath/{id}"

        const val saveByAdmin = "$protectedPath/admin/save"
        const val updateByAdmin = "$protectedPath/admin/update"
        const val findPaginated = "$protectedPath/paginated"
        const val findSearchByEmail = "$protectedPath/search"
    }
}