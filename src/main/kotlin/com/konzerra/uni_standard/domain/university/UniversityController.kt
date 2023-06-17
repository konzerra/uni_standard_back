package com.konzerra.uni_standard.domain.university

import com.fasterxml.jackson.databind.ObjectMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.university.dto.UniversityResponseDto
import com.konzerra.uni_standard.domain.university.dto.UniversitySaveDto
import com.konzerra.uni_standard.domain.university.dto.UniversityUpdateDto
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

@RestController
class UniversityController(
    private val universityService: UniversityService
) {

    @PutMapping(UniversityApi.update)
    fun update(@RequestBody updateDto: UniversityUpdateDto): ResponseEntity<*> {
        universityService.update(updateDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PostMapping(UniversityApi.save)
    fun save(@RequestBody saveDto: UniversitySaveDto): ResponseEntity<*> {
        universityService.save(saveDto)
        return ResponseEntity<Any>(HttpStatus.CREATED)
    }

    @DeleteMapping(UniversityApi.deleteById)
    fun deleteById(@PathVariable id: Long): ResponseEntity<*> {
        universityService.deleteById(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @GetMapping(UniversityApi.findById)
    fun findById(@PathVariable id: Long): ResponseEntity<UniversityResponseDto> {
        return ResponseEntity(universityService.findById(id),HttpStatus.OK)
    }

    @GetMapping(UniversityApi.findAll)
    fun findAll(): ResponseEntity<List<UniversityResponseDto>> {
        return ResponseEntity(universityService.findAll(), HttpStatus.OK)
    }

    @GetMapping(UniversityApi.findPaginated)
    fun findPaginated(
        @RequestParam("pageRequestDto") pageRequestDtoEncoded: String
    ): ResponseEntity<Page<UniversityResponseDto>> {
        val pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, "UTF-8")
        val pageRequestDto = ObjectMapper().readValue(pageRequestJson, PageRequestDto::class.java)
        return ResponseEntity(
            universityService.findPaginated(pageRequestDto),
            HttpStatus.OK
        )
    }
}
