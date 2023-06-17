package com.konzerra.uni_standard.domain.standard.port

import com.konzerra.uni_standard.domain.standard.Standard
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface StandardPort {
    /**
     * Save a Standard in the database
     *
     * @param standard The Standard entity to be saved
     */
    fun save(standard: Standard)

    /**
     * Delete a Standard from the database by id
     *
     * @param id The id of the Standard entity to be deleted
     */
    fun deleteById(id: Long)

    /**
     * Find all Standards by Status in the database
     *
     * @return A list of all Standards
     */
    fun findAllByStatus(status: String): List<Standard>

    /**
     * Find all Standards in the database, with pagination
     *
     * @param pageable The Pageable object defining the pagination parameters
     * @return A page of Standards
     */
    fun findPaginated(pageable: Pageable): Page<Standard>

    /**
     * Find a Standard by id
     *
     * @param id The id of the Standard to find
     * @return The found Standard, or null if no Standard was found with the given id
     */
    fun findById(id: Long): Standard
}
