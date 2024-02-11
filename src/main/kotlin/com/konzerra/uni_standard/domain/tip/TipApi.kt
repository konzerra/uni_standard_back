
package  com.konzerra.uni_standard.domain.tip

import com.konzerra.uni_standard.ApiPath


class TipApi {
    companion object{
        private const val protectedPath = "${ApiPath.protectedPath}/tip"
        private const val publicPath = "${ApiPath.publicPath}/tip"


        const val findAll = "$publicPath/all"
        const val findByIdFull = "$protectedPath/full/{id}"
        const val findById = "$protectedPath/{id}"
        const val findPaginated = "$publicPath/paginated"

        const val save = protectedPath
        const val update = protectedPath
        const val deleteById = "$protectedPath/{id}"
    }
}
    