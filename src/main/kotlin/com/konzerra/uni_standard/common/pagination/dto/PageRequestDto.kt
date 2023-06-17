package com.konzerra.uni_standard.common.pagination.dto

data class PageRequestDto(
    val page: Int,
    val size: Int,
    val sort: List<SortDto>
){
    constructor() : this(0, 0, emptyList())
}
