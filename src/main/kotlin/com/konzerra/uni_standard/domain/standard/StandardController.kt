package com.konzerra.uni_standard.domain.standard

import com.fasterxml.jackson.databind.ObjectMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.standard.dto.StandardResponseDto
import com.konzerra.uni_standard.domain.standard.dto.StandardSaveDto
import com.konzerra.uni_standard.domain.standard.dto.StandardUpdateDto
import com.konzerra.uni_standard.domain.user.UserRoles
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

@RestController
class StandardController(
    private val standardService: StandardService
) {
    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @PutMapping(StandardApi.update)
    fun update(@RequestBody @Valid updateDto: StandardUpdateDto): ResponseEntity<*> {
        standardService.update(updateDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @PostMapping(StandardApi.save)
    fun save(@RequestBody @Valid saveDto: StandardSaveDto): ResponseEntity<*> {
        standardService.save(saveDto)
        return ResponseEntity<Any>(HttpStatus.CREATED)
    }

    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
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

    @PreAuthorize("hasRole('Admin')")
    @GetMapping(StandardApi.findAllByStatus)
    fun findAllByStatus(
        @RequestParam status: String,
        @RequestHeader("Accept-Language") lang: String
    ): ResponseEntity<List<StandardResponseDto>> {
        return ResponseEntity(standardService.findAllByStatus(status, lang), HttpStatus.OK)
    }

    @GetMapping(StandardApi.findPaginated)
    fun findPaginated(
        @RequestParam("pageRequestDto") pageRequestDtoEncoded: String,
        @RequestHeader("Accept-Language") lang:String
    ): Any {
        val pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, "UTF-8")
        val pageRequestDto = ObjectMapper().readValue(pageRequestJson, PageRequestDto::class.java)
        return ResponseEntity(
            standardService.findPaginated(pageRequestDto, lang),
            HttpStatus.OK
        )
    }

    @GetMapping(StandardApi.findPaginatedWithReports)
    fun findPaginatedWithReports(
        @RequestParam("pageRequestDto") pageRequestDtoEncoded: String,
        @RequestHeader("Accept-Language") lang:String
    ): Any {
        val pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, "UTF-8")
        val pageRequestDto = ObjectMapper().readValue(pageRequestJson, PageRequestDto::class.java)
        return ResponseEntity(
            standardService.findPaginatedWithReports(pageRequestDto, lang),
            HttpStatus.OK
        )
    }


    @GetMapping(StandardApi.findAllPublishedWithReports)
    fun findPaginatedWithReports(
    ): Any {
        return ResponseEntity(
            standardService.findAllPublishedWithReports(),
            HttpStatus.OK
        )
    }
}
