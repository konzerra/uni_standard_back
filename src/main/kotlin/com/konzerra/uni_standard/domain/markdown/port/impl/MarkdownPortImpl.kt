package com.konzerra.uni_standard.domain.markdown.port.impl

import com.konzerra.uni_standard.domain.markdown.Markdown
import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.domain.markdown.MarkdownRepository
import com.konzerra.uni_standard.domain.markdown.port.MarkdownPort
import com.konzerra.uni_standard.exception.BadRequestException
import com.konzerra.uni_standard.exception.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Port
class MarkdownPortImpl(
    private val repository: MarkdownRepository
): MarkdownPort {
    override fun deleteById(id: String) {
        try{
            repository.deleteById(id)
        }catch(e:Exception){
            throw ResourceNotFoundException(
                report = "Страница с id: $id не найдена",
            )
        }
    }

    override fun findAll(): List<Markdown> {
        return repository.findAll()
    }

    override fun findAllPaginated(pageable: Pageable): Page<Markdown> {
        return repository.findAll(pageable)
    }

    override fun findById(id: String): Markdown {
        return repository.findById(id).orElseThrow {
            ResourceNotFoundException(
                report = "Страница с id: $id не найдена",
            )
        }
    }

    override fun findByName(name: String): Markdown {
        repository.findByName(name)?.let { markdown ->
            return markdown
        }
        throw ResourceNotFoundException(
            report = "Страница с названием: $name не найдена",
        )
    }

    override fun save(markdown: Markdown) {

        try {
            repository.save(markdown)
        }catch (e:RuntimeException){
            throw BadRequestException(
                    "the_same_markdown_id"
                )
        }

    }
}


