package com.konzerra.uni_standard.domain.markdown

import com.fasterxml.jackson.databind.ObjectMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.markdown.dto.MarkdownResponseDto
import com.konzerra.uni_standard.domain.markdown.dto.MarkdownSaveDto
import com.konzerra.uni_standard.domain.markdown.dto.MarkdownUpdateDto
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URLDecoder

@RestController
class MarkdownController(
    private val markdownService: MarkdownService
) {

    @GetMapping(MarkdownApi.findAll)
    fun findAllNames(
        @RequestHeader("Accept-Language") lang: String
    ): ResponseEntity<List<MarkdownResponseDto>> {
        return ResponseEntity(
            markdownService.findAll(lang),
            HttpStatus.OK
        )
    }

    @GetMapping(MarkdownApi.findPaginated)
    fun findPaginated(
        @RequestParam("pageRequestDto") pageRequestDtoEncoded: String,
        @RequestHeader("Accept-Language") lang: String
    ): ResponseEntity<Page<MarkdownResponseDto>> {
        val pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, "UTF-8")
        val pageRequestDto = ObjectMapper().readValue(pageRequestJson, PageRequestDto::class.java)
        return ResponseEntity(
            markdownService.findAllPaginated(pageRequestDto, lang),
            HttpStatus.OK
        )
    }


    @GetMapping(MarkdownApi.findById)
    fun findById(
        @PathVariable id: String,
        @RequestHeader("Accept-Language") lang: String
        ):ResponseEntity<MarkdownResponseDto>{
        return ResponseEntity(
            markdownService.findById(id, lang),
            HttpStatus.OK
        )
    }
    @GetMapping(MarkdownApi.findByName)
    fun findByName(
        @PathVariable name: String,
        @RequestHeader("Accept-Language") lang: String
    ): ResponseEntity<MarkdownResponseDto>{
        return ResponseEntity(
            markdownService.findByName(name,lang),
            HttpStatus.OK
        )
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping(MarkdownApi.deleteById)
    fun deleteById(@PathVariable id: String): Any {
        markdownService.deleteById(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(MarkdownApi.save)
    fun save(
        @RequestPart saveDto: MarkdownSaveDto,
        @RequestPart image: MultipartFile
    ): Any {
        markdownService.save(saveDto, image)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping(MarkdownApi.update)
    fun update(
        @RequestPart updateDto: MarkdownUpdateDto,
        @RequestPart(required = false) image: MultipartFile?
    ): Any {
        markdownService.update(updateDto, image)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

}