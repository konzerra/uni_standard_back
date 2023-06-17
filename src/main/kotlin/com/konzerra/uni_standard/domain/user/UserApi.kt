package com.konzerra.uni_standard.domain.user

import com.konzerra.uni_standard.ApiPath


class UserApi {
    companion object{
        private const val basePath = "/user"
        const val protectedPath = "${ApiPath.protectedPath}$basePath"
        const val publicPath = "${ApiPath.publicPath}$basePath"

        const val findById = "$protectedPath/{id}"
        const val findAll = "$publicPath/all/"

        const val getBalance = "$protectedPath/{id}/balance"

        const val updatePath = protectedPath
        const val deleteById = "$protectedPath/{id}"


    }
}