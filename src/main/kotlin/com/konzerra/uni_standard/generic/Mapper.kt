package com.konzerra.uni_standard.generic

import com.konzerra.uni_standard.AppLanguages


interface Mapper<Entity, ResponseDto> {
   fun toDto(entity:Entity, lang:String = AppLanguages.DEFAULT): ResponseDto
}