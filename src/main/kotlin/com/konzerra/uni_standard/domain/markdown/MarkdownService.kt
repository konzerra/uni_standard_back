package com.konzerra.uni_standard.domain.markdown


import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.markdown.dto.MarkdownResponseDto
import com.konzerra.uni_standard.domain.markdown.dto.MarkdownSaveDto
import com.konzerra.uni_standard.domain.markdown.dto.MarkdownUpdateDto
import org.springframework.data.domain.Page
import org.springframework.web.multipart.MultipartFile

interface MarkdownService {

    fun save(
        saveDto: MarkdownSaveDto,
        image: MultipartFile
    )

    fun update(
        updateDto: MarkdownUpdateDto,
        image: MultipartFile?
    )

    fun deleteById(id:String)

    fun findAll(lang: String):List<MarkdownResponseDto>

    fun findAllPaginated(pageRequestDto: PageRequestDto, lang: String):Page<MarkdownResponseDto>

    fun findById(id: String, lang: String): MarkdownResponseDto

    fun findByName(name: String, lang: String): MarkdownResponseDto
}