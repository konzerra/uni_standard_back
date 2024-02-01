package com.konzerra.uni_standard.domain.tip

import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.tip.dto.TipResponseDto
import com.konzerra.uni_standard.domain.tip.dto.TipSaveDto
import com.konzerra.uni_standard.domain.tip.dto.TipUpdateDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TipController(
    private val tipService: TipService
) {
    @PutMapping(TipApi.update)
    fun update(@RequestBody updateDto: TipUpdateDto): ResponseEntity<*> {
        tipService.update(updateDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }


    @PostMapping(TipApi.save)
    fun save(@RequestBody saveDto: TipSaveDto): ResponseEntity<*> {
        tipService.save(saveDto)
        return ResponseEntity<Any>(HttpStatus.CREATED)
    }

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

    @PostMapping(TipApi.findAllPaginated)
    fun findAllPaginated(
        @RequestBody pageRequestDto: PageRequestDto,
        @RequestHeader("Accept-Language") lang: String
    ): ResponseEntity<Any> {
        return ResponseEntity(
            tipService.findAllPaginated(pageRequestDto,lang),
            HttpStatus.OK
        )
    }

}