package com.konzerra.uni_standard.domain.standard

import com.konzerra.uni_standard.ApiPath

class StandardApi {
    companion object{

        private const val protectedPath = "${ApiPath.protectedPath}/standard"
        private const val publicPath = "${ApiPath.publicPath}/standard"

        const val findPaginated = "$publicPath/paginated"
        const val findPaginatedWithReports = "$publicPath/reports/paginated"
        const val findById = "$protectedPath/{id}"
        const val findAllByStatus = "$publicPath/all_by_status"

        const val findAllPublishedWithReports = "$publicPath/with-reports"

        const val save = protectedPath
        const val update = protectedPath
        const val deleteById = "$protectedPath/{id}"
    }
}