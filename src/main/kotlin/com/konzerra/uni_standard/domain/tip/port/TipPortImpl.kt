package com.konzerra.uni_standard.domain.tip.port


import com.konzerra.uni_standard.domain.tip.Tip
import com.konzerra.uni_standard.domain.tip.TipRepository
import com.konzerra.uni_standard.annotation.Port
import com.konzerra.uni_standard.exception.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Port
class TipPortImpl(
    private val repository: TipRepository
) : TipPort {
    override fun save(tip: Tip) {
        repository.save(tip)
    }

    override fun deleteById(id: Long) {
        try{
            repository.deleteById(id)
        }catch(e:RuntimeException){
            throw ResourceNotFoundException(
                report = "Tip with id: $id not found"
            )

        }
    }

    override fun findAllPaginated(pageable: Pageable): Page<Tip> {
        return repository.findAll(pageable)
    }

    override fun findAll(): List<Tip> {
        return repository.findAll()
    }

    override fun findById(id: Long): Tip {
        return repository.findById(id).orElseThrow {
            throw ResourceNotFoundException(
                report = "Tip with id: $id not found"
            )
        }
    }
}