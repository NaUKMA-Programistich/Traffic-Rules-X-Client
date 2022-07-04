package data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    Traffic dto
 */
@Serializable
data class TrafficRuleDto(
    @SerialName("content")
    val content: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("number")
    val number: String,
    @SerialName("themeId")
    val themeId: Int
)
