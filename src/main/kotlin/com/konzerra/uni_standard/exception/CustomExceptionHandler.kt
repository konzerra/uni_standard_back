package com.konzerra.uni_standard.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class CustomExceptionHandler(
) {

    @ExceptionHandler
    fun handleResourceNotFound(exception: ResourceNotFoundException, request: WebRequest):Any{
        return ResponseEntity(ExceptionResponse(exception.report),HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler
    fun handleBadRequest(exception: BadRequestException, request: WebRequest):Any{
        return ResponseEntity(ExceptionResponse(exception.report),HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleUnauthorizedRequest(exception: UnauthorizedException, request: WebRequest):Any{
        return ResponseEntity(ExceptionResponse(exception.report),HttpStatus.UNAUTHORIZED)
    }

}