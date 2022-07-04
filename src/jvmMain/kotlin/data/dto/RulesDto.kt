package data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    Rules dto
 */
@Serializable
data class RulesDto(
    @SerialName("themes")
    val themes: List<ThemeDto>
)
