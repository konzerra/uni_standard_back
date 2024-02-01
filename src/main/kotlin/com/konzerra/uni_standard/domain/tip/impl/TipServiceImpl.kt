package com.konzerra.uni_standard.domain.tip.impl


import com.konzerra.uni_standard.domain.tip.Tip
import com.konzerra.uni_standard.domain.tip.TipService
import com.konzerra.uni_standard.domain.tip.dto.TipResponseDto
import com.konzerra.uni_standard.domain.tip.dto.TipSaveDto
import com.konzerra.uni_standard.domain.tip.dto.TipUpdateDto
import com.konzerra.uni_standard.common.pagination.PaginationMapper
import com.konzerra.uni_standard.common.pagination.dto.PageRequestDto
import com.konzerra.uni_standard.domain.tip.port.TipPort
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class TipServiceImpl(
    private val tipPort: TipPort
) : TipService {
    override fun findAll(lang: String): List<TipResponseDto> {
        return tipPort.findAll().map {
            TipResponseDto.toDto(it, lang)
        }
    }

    override fun findAllPaginated(pageRequestDto: PageRequestDto, lang: String): Page<TipResponseDto> {
        return tipPort.findAllPaginated(PaginationMapper.toPageable(pageRequestDto)).map {
            TipResponseDto.toDto(it, lang)
        }
    }


    override fun findById(id: Long, lang: String): TipResponseDto {
        return TipResponseDto.toDto(tipPort.findById(id), lang)
    }

    override fun deleteById(id: Long) {
        tipPort.deleteById(id)
    }

    override fun save(saveDto: TipSaveDto) {
        tipPort.save(
            Tip(
                question = saveDto.question,
                answer = saveDto.answer
            )
        )
    }

    override fun update(updateDto: TipUpdateDto) {
        val tip = tipPort.findById(updateDto.id)
        tip.question = updateDto.question
        tip.answer = updateDto.answer
        tipPort.save(tip)
    }
}