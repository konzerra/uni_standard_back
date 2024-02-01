package com.konzerra.uni_standard.domain.tip


import com.konzerra.uni_standard.AppLanguages
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.tip.dto.TipResponseDto
import com.konzerra.uni_standard.domain.tip.dto.TipSaveDto
import com.konzerra.uni_standard.domain.tip.dto.TipUpdateDto
import org.springframework.data.domain.Page

interface TipService {

    fun findAll(lang: String = AppLanguages.DEFAULT):List<TipResponseDto>

    fun findAllPaginated(pageRequestDto: PageRequestDto, lang: String = AppLanguages.DEFAULT): Page<TipResponseDto>


    fun findById(id:Long,lang: String = AppLanguages.DEFAULT): TipResponseDto

    fun deleteById(id:Long)

    fun save(saveDto: TipSaveDto)

    fun update(updateDto: TipUpdateDto)
}