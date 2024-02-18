package com.konzerra.uni_standard.domain.markdown

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface MarkdownRepository:JpaRepository<Markdown,String> {
    fun findByName(name: String): Markdown?
}