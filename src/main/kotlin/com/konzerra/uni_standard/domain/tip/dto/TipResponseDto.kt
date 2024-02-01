
package  com.konzerra.uni_standard.domain.tip.dto



import com.konzerra.uni_standard.domain.tip.Tip
import com.konzerra.uni_standard.generic.Mapper
import org.springframework.stereotype.Component


class TipResponseDto(
    var id:Long? = null,
    var question: String,
    var answer: String,
)
{
    @Component
    companion object : Mapper<Tip, TipResponseDto> {
        override fun toDto(entity: Tip, lang:String): TipResponseDto {
            return  TipResponseDto(
                id= entity.id,
                question = entity.question,
                answer = entity.answer
            )
        }
    }

}
    