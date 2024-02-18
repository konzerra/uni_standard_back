package com.konzerra.uni_standard.domain.markdown.port

import com.konzerra.uni_standard.domain.markdown.Markdown
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MarkdownPort {

    fun deleteById(id:String)

    fun findAll():List<Markdown>

    fun findAllPaginated(pageable: Pageable):Page<Markdown>

    fun findById(id: String): Markdown

    fun findByName(name: String): Markdown

    fun save(markdown: Markdown)
}