package data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    Exam dto
 */
@Serializable
data class ExamDto(
    @SerialName("questionList")
    val questionList: List<QuestionDto>
)
