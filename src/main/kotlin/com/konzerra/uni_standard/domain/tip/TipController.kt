package com.konzerra.uni_standard.domain.tip

import com.fasterxml.jackson.databind.ObjectMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.tip.dto.TipResponseDto
import com.konzerra.uni_standard.domain.tip.dto.TipSaveDto
import com.konzerra.uni_standard.domain.tip.dto.TipUpdateDto
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

@RestController
class TipController(
    private val tipService: TipService
) {
    @PreAuthorize("hasRole('Admin')")
    @PutMapping(TipApi.update)
    fun update(@RequestBody updateDto: TipUpdateDto): ResponseEntity<*> {
        tipService.update(updateDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }


    @PreAuthorize("hasRole('Admin')")
    @PostMapping(TipApi.save)
    fun save(@RequestBody saveDto: TipSaveDto): ResponseEntity<*> {
        tipService.save(saveDto)
        return ResponseEntity<Any>(HttpStatus.CREATED)
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping(TipApi.deleteById)
    fun deleteById(@PathVariable id: Long): ResponseEntity<*> {
        tipService.deleteById(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
    @GetMapping(TipApi.findById)
    fun findById(
        @PathVariable id: Long,
        @RequestHeader("Accept-Language") lang: String
    ): ResponseEntity<TipResponseDto> {
        return ResponseEntity(tipService.findById(id,lang),HttpStatus.OK)
    }
    @GetMapping(TipApi.findAll)
    fun findAll(@RequestHeader("Accept-Language") lang: String): ResponseEntity<List<TipResponseDto>> {
        return ResponseEntity(tipService.findAll(lang), HttpStatus.OK)
    }

    @GetMapping(TipApi.findPaginated)
    fun findPaginated(
        @RequestParam("pageRequestDto") pageRequestDtoEncoded: String,
        @RequestHeader("Accept-Language") lang:String
    ): ResponseEntity<Page<TipResponseDto>> {
        val pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, "UTF-8")
        val pageRequestDto = ObjectMapper().readValue(pageRequestJson, PageRequestDto::class.java)
        return ResponseEntity(
            tipService.findAllPaginated(pageRequestDto,lang),
            HttpStatus.OK
        )
    }

}