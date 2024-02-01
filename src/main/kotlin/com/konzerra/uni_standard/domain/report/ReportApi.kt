package com.konzerra.uni_standard.domain.report

import com.konzerra.uni_standard.ApiPath

class ReportApi {
    companion object{

        private const val protectedPath = "${ApiPath.protectedPath}/report"
        private const val publicPath = "${ApiPath.publicPath}/report"

        const val findById = "$publicPath/{id}"
        const val findAll = "$publicPath/all"

        const val save = protectedPath
        const val update = protectedPath
        const val deleteById = "$protectedPath/{id}"
    }
}