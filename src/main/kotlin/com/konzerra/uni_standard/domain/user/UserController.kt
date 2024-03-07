package com.konzerra.uni_standard.domain.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.user.dto.UserAdminUpdateDto
import com.konzerra.uni_standard.domain.user.dto.UserResponseDto
import com.konzerra.uni_standard.domain.user.dto.UserSaveDto
import com.konzerra.uni_standard.domain.user.dto.UserUpdateDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

@RestController
class UserController(
    private val userService: UserService
) {

    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @GetMapping(UserApi.findById)
    fun findById(@PathVariable id: Long,@RequestHeader("Accept-Language") lang:String): ResponseEntity<UserResponseDto> {
        return ResponseEntity(userService.findById(id, lang),HttpStatus.OK)
    }


    @PutMapping(UserApi.update)
    fun update(@RequestBody updateDto: UserUpdateDto): ResponseEntity<*> {
        userService.update(updateDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @PostMapping(UserApi.saveByAdmin)
    fun saveByAdmin(@RequestBody saveDto: UserSaveDto): ResponseEntity<*> {
        userService.saveUser(saveDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @PutMapping(UserApi.updateByAdmin)
    fun updateByAdmin(@RequestBody updateDto: UserAdminUpdateDto): ResponseEntity<*> {
        userService.updateByAdmin(updateDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @DeleteMapping(UserApi.deleteById)
    fun deleteById(@PathVariable id: Long): ResponseEntity<*> {
        userService.deleteById(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @GetMapping(UserApi.findPaginated)
    fun findPaginated(
        @RequestParam("pageRequestDto") pageRequestDtoEncoded: String,
        @RequestHeader("Accept-Language") lang:String
    ): Any {
        val pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, "UTF-8")
        val pageRequestDto = ObjectMapper().readValue(pageRequestJson, PageRequestDto::class.java)
        return ResponseEntity(
            userService.findPaginated(pageRequestDto),
            HttpStatus.OK
        )
    }
    @PreAuthorize("hasAnyRole('${UserRoles.ADMIN}')")
    @GetMapping(UserApi.findSearchByEmail)
    fun search(
        @RequestParam("pageRequestDto") pageRequestDtoEncoded: String,
        @RequestParam("email") email: String,
        @RequestHeader("Accept-Language") lang:String
    ): Any {
        println(pageRequestDtoEncoded)
        val pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, "UTF-8")
        val pageRequestDto = ObjectMapper().readValue(pageRequestJson, PageRequestDto::class.java)
        return ResponseEntity(
            userService.searchByEmail(pageRequestDto, email),
            HttpStatus.OK
        )
    }
}