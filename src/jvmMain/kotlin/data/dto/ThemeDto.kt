package data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    Theme dto
 */
@Serializable
data class ThemeDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("number")
    val number: String,
    @SerialName("trafficRules")
    val trafficRules: List<TrafficRuleDto>
)
