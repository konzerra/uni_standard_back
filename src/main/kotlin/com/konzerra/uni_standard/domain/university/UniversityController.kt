package com.konzerra.uni_standard.domain.university

import com.fasterxml.jackson.databind.ObjectMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.university.dto.UniversityResponseDto
import com.konzerra.uni_standard.domain.university.dto.UniversitySaveDto
import com.konzerra.uni_standard.domain.university.dto.UniversityUpdateDto
import com.konzerra.uni_standard.domain.user.UserRoles
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

@RestController
class UniversityController(
    private val universityService: UniversityService
) {

    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @PutMapping(UniversityApi.update)
    fun update(@RequestBody updateDto: UniversityUpdateDto): ResponseEntity<UniversityResponseDto> {

        return ResponseEntity<UniversityResponseDto>(universityService.update(updateDto),HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @PostMapping(UniversityApi.save)
    fun save(@RequestBody saveDto: UniversitySaveDto): ResponseEntity<UniversityResponseDto> {

        return ResponseEntity<UniversityResponseDto>(universityService.save(saveDto), HttpStatus.CREATED)
    }

    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
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
