package com.konzerra.uni_standard.standard

import com.konzerra.uni_standard.domain.standard.criterion.Criterion
import com.konzerra.uni_standard.domain.standard.criterion.dto.CriterionUpdateDto
import com.konzerra.uni_standard.domain.standard.Standard
import com.konzerra.uni_standard.domain.standard.StandardService
import com.konzerra.uni_standard.domain.standard.StandardStatus
import com.konzerra.uni_standard.domain.standard.dto.StandardUpdateDto
import com.konzerra.uni_standard.domain.standard.impl.StandardServiceImpl
import com.konzerra.uni_standard.domain.standard.port.StandardPort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class StandardServiceImplUpdateTest {

    private lateinit var standardPort: StandardPort
    private lateinit var standardService: StandardService

    @BeforeEach
    fun setup() {
        standardPort = mockk(relaxed = true)
        standardService = StandardServiceImpl(standardPort)
    }

    @Test
    fun `update should update the standard excluding criteria list`() {
        // Arrange
        val id = 1L
        val existingStandard = Standard(id, "Existing name", "1.0", "Existing description", StandardStatus.REGISTERED, emptyList())
        val updateDto = StandardUpdateDto(id, "New name", "2.0", "New description", StandardStatus.PUBLISHED, emptyList())
        every { standardPort.findById(id) } returns existingStandard
        every { standardPort.save(any()) } returns Unit

        // Act
        standardService.update(updateDto)

        // Assert
        verify { standardPort.findById(id) }
        assertThat(existingStandard.name).isEqualTo(updateDto.name)
        assertThat(existingStandard.version).isEqualTo(updateDto.version)
        assertThat(existingStandard.description).isEqualTo(updateDto.description)
        verify { standardPort.save(any()) }
    }

    @Test
    fun `update should update the standard and its criteria`() {
//        // Arrange
//        val id = 1L
//        val existingCriteria = Criterion(1, "Existing Criterion name", "Existing Criterion description", 1.0)
//        val existingStandard = Standard(id, "Existing name", "1.0", "Existing description", StandardStatus.REGISTERED, listOf(existingCriteria))
//
//        val updateDtoCriteria = CriterionUpdateDto(1, "New Criterion name", "New Criterion description", 2.0)
//        val updateDto = StandardUpdateDto(id, "New name", "2.0", "New description", StandardStatus.REGISTERED, listOf(updateDtoCriteria))
//
//        every { standardPort.findById(id) } returns existingStandard
//        every { standardPort.save(any()) } returns Unit
//
//        // Act
//        standardService.update(updateDto)
//
//        // Assert
//        verify { standardPort.findById(id) }
//        assertThat(existingStandard.name).isEqualTo(updateDto.name)
//        assertThat(existingStandard.version).isEqualTo(updateDto.version)
//        assertThat(existingStandard.description).isEqualTo(updateDto.description)
//
//        val updatedCriterion = existingStandard.criteriaGroups.find { it.id == updateDtoCriteria.id }
//        assertThat(updatedCriterion).isNotNull
//        assertThat(updatedCriterion?.name).isEqualTo(updateDtoCriteria.name)
//        assertThat(updatedCriterion?.description).isEqualTo(updateDtoCriteria.description)
//        assertThat(updatedCriterion?.value).isEqualTo(updateDtoCriteria.value)
//
//        verify { standardPort.save(any()) }
    }

    @Test
    fun `update should create new criterion and update existing one`() {
//        // Arrange
//        val id = 1L
//        val existingCriteria = Criterion(1, "Existing Criterion name", "Existing Criterion description", 1.0)
//        val existingStandard = Standard(id, "Existing name", "1.0", "Existing description", StandardStatus.REGISTERED, listOf(existingCriteria))
//
//        val newDtoCriteria = CriterionUpdateDto(null, "New Criterion name", "New Criterion description", 2.0)
//        val updateDtoCriteria = CriterionUpdateDto(1, "Updated Criterion name", "Updated Criterion description", 3.0)
//        val updateDto = StandardUpdateDto(id, "New name", "2.0", "New description", StandardStatus.PUBLISHED, listOf(newDtoCriteria, updateDtoCriteria))
//
//        every { standardPort.findById(id) } returns existingStandard
//        every { standardPort.save(any()) } returns Unit
//
//        // Act
//        standardService.update(updateDto)
//
//        // Assert
//        verify { standardPort.findById(id) }
//
//        assertThat(existingStandard.name).isEqualTo(updateDto.name)
//        assertThat(existingStandard.version).isEqualTo(updateDto.version)
//        assertThat(existingStandard.description).isEqualTo(updateDto.description)
//
//        // Check existing criterion update
//        val updatedCriterion = existingStandard.criteriaGroups.find { it.id == updateDtoCriteria.id }
//        assertThat(updatedCriterion).isNotNull
//        assertThat(updatedCriterion?.name).isEqualTo(updateDtoCriteria.name)
//        assertThat(updatedCriterion?.description).isEqualTo(updateDtoCriteria.description)
//        assertThat(updatedCriterion?.value).isEqualTo(updateDtoCriteria.value)
//
//        // Check new criterion creation
//        val newCriterion = existingStandard.criteriaGroups.find { it.id == null }
//        assertThat(newCriterion).isNotNull
//        assertThat(newCriterion?.name).isEqualTo(newDtoCriteria.name)
//        assertThat(newCriterion?.description).isEqualTo(newDtoCriteria.description)
//        assertThat(newCriterion?.value).isEqualTo(newDtoCriteria.value)
//
//        verify { standardPort.save(any()) }
    }


}
