package com.konzerra.uni_standard.domain.markdown.dto


import com.konzerra.uni_standard.domain.markdown.Markdown
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component


class MarkdownResponseDto(
    var id: String,
    var name:String,
    var source: String,
    var image: String,
    var priority: Int
) {

    @Component
    companion object: Mapper<Markdown, MarkdownResponseDto> {
        override fun toDto(entity: Markdown, lang: String): MarkdownResponseDto {
            return MarkdownResponseDto(
                id=entity.id ?: "",
                name=entity.name,
                source = entity.source,
                image= entity.image,
                priority = entity.priority
            )
        }

    }
}