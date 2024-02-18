package com.konzerra.uni_standard.domain.markdown

import com.konzerra.uni_standard.ApiPath


class MarkdownApi {
    companion object{
        private const val protectedPath = "${ApiPath.protectedPath}/markdown"
        private const val publicPath = "${ApiPath.publicPath}/markdown"



        const val findByName = "$publicPath/by_name/{name}"
        const val findById = "$publicPath/{id}"

        const val findPaginated = "$protectedPath/paginated"
        const val findAll = "$publicPath/all"

        const val save = protectedPath
        const val update = protectedPath
        const val deleteById = "$protectedPath/{id}"
    }
}