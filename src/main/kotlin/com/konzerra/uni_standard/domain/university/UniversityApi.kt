package com.konzerra.uni_standard.domain.university

import com.konzerra.uni_standard.ApiPath

class UniversityApi {
    companion object{

        private const val protectedPath = "${ApiPath.protectedPath}/university"
        private const val publicPath = "${ApiPath.publicPath}/university"

        const val findPaginated = "$protectedPath/paginated"
        const val findById = "$publicPath/{id}"
        const val findAll = "$publicPath/all"

        const val save = protectedPath
        const val update = protectedPath
        const val deleteById = "$protectedPath/{id}"
    }
}