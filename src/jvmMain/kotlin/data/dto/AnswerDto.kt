package data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    Answer dto
 */
@Serializable
data class AnswerDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)
