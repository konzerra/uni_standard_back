package com.konzerra.uni_standard.domain.standard

import com.konzerra.uni_standard.AppLanguages
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.standard.dto.StandardReportsResponseDto
import com.konzerra.uni_standard.domain.standard.dto.StandardResponseDto
import com.konzerra.uni_standard.domain.standard.dto.StandardSaveDto
import com.konzerra.uni_standard.domain.standard.dto.StandardUpdateDto
import org.springframework.data.domain.Page

interface StandardService {
    /**
     * Find all Standards in the database
     *
     * @param lang The language for the returned DTOs
     * @param status The status for filtration
     * @return A list of all StandardResponseDto
     */
    fun findAllByStatus(status: String,lang: String = AppLanguages.DEFAULT): List<StandardResponseDto>

    /**
     * Find all Standards in the database, with pagination
     *
     * @param pageRequestDto The PageRequestDto object defining the pagination parameters
     * @param lang The language for the returned DTOs
     * @return A page of StandardResponseDto
     */
    fun findPaginated(pageRequestDto: PageRequestDto, lang: String = AppLanguages.DEFAULT): Page<StandardResponseDto>


    fun findPaginatedWithReports(pageRequestDto: PageRequestDto, lang: String = AppLanguages.DEFAULT): Page<StandardReportsResponseDto>

    /**
     * Find a Standard by id
     *
     * @param id The id of the Standard to find
     * @param lang The language for the returned DTOs
     * @return The found StandardResponseDto
     */
    fun findById(id: Long, lang: String = AppLanguages.DEFAULT): StandardResponseDto


    /**
     * Delete a Standard by id
     *
     * @param id The id of the Standard to delete
     */
    fun deleteById(id: Long)

    /**
     * Save a new Standard
     *
     * @param saveDto The StandardSaveDto object defining the details of the Standard to save
     */
    fun save(saveDto: StandardSaveDto)

    /**
     * Update an existing Standard
     *
     * @param updateDto The StandardUpdateDto object defining the details of the Standard to update
     */
    fun update(updateDto: StandardUpdateDto)

    fun findAllPublishedWithReports(): List<StandardReportsResponseDto>
}
