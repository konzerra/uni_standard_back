package com.konzerra.uni_standard.domain.user

import com.konzerra.uni_standard.domain.user.dto.UserResponseDto
import com.konzerra.uni_standard.domain.user.dto.UserUpdateDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService
) {

    @GetMapping(UserApi.findById)
    fun findById(@PathVariable id: Long,@RequestHeader("Accept-Language") lang:String): ResponseEntity<UserResponseDto> {
        return ResponseEntity(userService.findById(id, lang),HttpStatus.OK)
    }

    @PutMapping(UserApi.updatePath)
    fun update(@RequestBody updateDto: UserUpdateDto): ResponseEntity<*> {
        userService.update(updateDto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

}