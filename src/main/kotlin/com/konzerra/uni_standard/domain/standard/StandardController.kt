package com.konzerra.uni_standard.domain.standard

import com.fasterxml.jackson.databind.ObjectMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.standard.dto.StandardResponseDto
import com.konzerra.uni_standard.domain.standard.dto.StandardSaveDto
import com.konzerra.uni_standard.domain.standard.dto.StandardUpdateDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

@RestController
class StandardController(
    private val standardService: StandardService
) {
    @PutMapping(StandardApi.update)
    fun update(@RequestBody updateDto: StandardUpdateDto): ResponseEntity<*> {
        standardService.update(updateDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PostMapping(StandardApi.save)
    fun save(@RequestBody saveDto: StandardSaveDto): ResponseEntity<*> {
        standardService.save(saveDto)
        return ResponseEntity<Any>(HttpStatus.CREATED)
    }

    @DeleteMapping(StandardApi.deleteById)
    fun deleteById(@PathVariable id: Long): ResponseEntity<*> {
        standardService.deleteById(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @GetMapping(StandardApi.findById)
    fun findById(
        @PathVariable id: Long,
        @RequestHeader("Accept-Language") lang: String
    ): ResponseEntity<StandardResponseDto> {
        return ResponseEntity(standardService.findById(id,lang),HttpStatus.OK)
    }

    @GetMapping(StandardApi.findAllByStatus)
    fun findAllByStatus(
        @RequestParam status: String,
        @RequestHeader("Accept-Language") lang: String
    ): ResponseEntity<List<StandardResponseDto>> {
        return ResponseEntity(standardService.findAllByStatus(status, lang), HttpStatus.OK)
    }

    @GetMapping(StandardApi.findPaginated)
    fun findAllPaginated(
        @RequestParam("pageRequestDto") pageRequestDtoEncoded: String,
        @RequestHeader("Accept-Language") lang:String
    ): Any {
        val pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, "UTF-8")
        val pageRequestDto = ObjectMapper().readValue(pageRequestJson, PageRequestDto::class.java)
        return ResponseEntity(
            standardService.findAllPaginated(pageRequestDto, lang),
            HttpStatus.OK
        )
    }
}
