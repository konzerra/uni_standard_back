package com.konzerra.uni_standard.domain.markdown.impl


import com.konzerra.uni_standard.domain.markdown.Markdown
import com.konzerra.uni_standard.domain.markdown.MarkdownService
import com.konzerra.uni_standard.common.pagination.PaginationMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.file.FileStorageService
import com.konzerra.uni_standard.domain.markdown.dto.MarkdownResponseDto
import com.konzerra.uni_standard.domain.markdown.dto.MarkdownSaveDto
import com.konzerra.uni_standard.domain.markdown.dto.MarkdownUpdateDto
import com.konzerra.uni_standard.domain.markdown.port.MarkdownPort
import com.konzerra.uni_standard.exception.BadRequestException
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class MarkdownServiceImpl(
    private val markdownPort: MarkdownPort,
    private val fileStorageService: FileStorageService
) : MarkdownService {
    override fun save(saveDto: MarkdownSaveDto,image: MultipartFile) {
        try {
            markdownPort.findById(saveDto.id)
            throw BadRequestException(
                "Запись с id: ${saveDto.id} уже существует"
            )
        }
        catch (e:Exception){
            markdownPort.save(
                Markdown(
                    id = saveDto.id,
                    name = saveDto.name,
                    image= fileStorageService.save(image),
                    source = saveDto.source,
                    priority = saveDto.priority
                )
            )
        }

    }

    override fun update(updateDto: MarkdownUpdateDto,image: MultipartFile?) {
        val markdown = markdownPort.findById(updateDto.id)
        markdown.name = updateDto.name
        markdown.source = updateDto.source
        markdown.priority=updateDto.priority
        image?.let {
            markdown.image = fileStorageService.save(image,markdown.image)
        }
        markdownPort.save(markdown)
    }

    override fun deleteById(id: String) {
        val markdown = markdownPort.findById(id)
        fileStorageService.delete(markdown.image)
        markdownPort.deleteById(id)
    }

    override fun findAll(lang: String): List<MarkdownResponseDto> {
        return markdownPort.findAll().map {
            MarkdownResponseDto(
                id= it.id ?: "",
                name = it.name,
                priority = it.priority,
                source = it.source,
                image = it.image
            )

        }.sortedByDescending {
            it.priority
        }
    }

    override fun findAllPaginated(pageRequestDto: PageRequestDto, lang: String): Page<MarkdownResponseDto> {
        return markdownPort.findAllPaginated(PaginationMapper.toPageable(pageRequestDto)).map {
            MarkdownResponseDto.toDto(it, lang)
        }
    }

    override fun findById(id: String, lang: String): MarkdownResponseDto {
        return MarkdownResponseDto.toDto(
            markdownPort.findById(id),
            lang
        )
    }


    override fun findByName(name: String, lang: String): MarkdownResponseDto {
        return MarkdownResponseDto.toDto(markdownPort.findByName(name))
    }
}