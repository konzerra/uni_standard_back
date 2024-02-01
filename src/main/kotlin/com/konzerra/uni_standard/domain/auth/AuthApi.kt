package com.konzerra.uni_standard.domain.auth

import com.konzerra.uni_standard.ApiPath

class AuthApi {
    companion object{
        private const val basePath = "/auth"
        const val protectedPath = "${ApiPath.protectedPath}$basePath"
        const val publicPath = "${ApiPath.publicPath}$basePath"

        const val signup = "$publicPath/signup"
        const val signin = "$publicPath/signin"
    }
}