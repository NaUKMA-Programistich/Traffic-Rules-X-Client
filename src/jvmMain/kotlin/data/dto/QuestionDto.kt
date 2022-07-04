package data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    Question dto
 */
@Serializable
data class QuestionDto(
    @SerialName("answers")
    val answers: List<AnswerDto>,
    @SerialName("correctAnswerId")
    val correctAnswerId: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("pictureLink")
    val pictureLink: String?,
    @SerialName("explanation")
    val explanation: String?
)
