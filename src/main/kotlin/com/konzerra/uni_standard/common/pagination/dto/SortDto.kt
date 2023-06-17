package com.konzerra.uni_standard.common.pagination.dto

data class SortDto(
    val property: String,
    val direction: String
){
    constructor() : this("","")
}