package com.konzerra.uni_standard.exception

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ExceptionResponse(
    val message:String = "Неизвестное сообщение об ошибке"
)