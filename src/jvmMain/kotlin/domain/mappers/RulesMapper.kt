package domain.mappers

import data.dto.RulesDto
import data.dto.ThemeDto
import data.dto.TrafficRuleDto
import domain.models.Point
import domain.models.Rule

/*
    Convertor from rules dto to rule data
 */
object RulesMapper {

    /*
        Convertor from rules dto to list rule
    */
    fun RulesDto.mapper(): List<Rule> {
        return this.themes.map { it.mapper() }
    }

    /*
        Convertor from theme dto to rule
     */
    fun ThemeDto.mapper(): Rule {
        return Rule(
            number = this.number,
            title = this.name,
            points = this.trafficRules.map { it.mapper() }
        )
    }
    /*
        Convertor from rule dto to point with some replace
     */
    fun TrafficRuleDto.mapper(): Point {
        return Point(
            number = this.number,
            content = this.content
                .replace("\\r\\n!", "\n!")
                .replace(")\\r\\n", ")\n")
                .replace("\\r\\n\\r\\n\"", "\n")
                .replace("\\r\\n\\r\\n-", "")
                .replace("\\r\\n\\r\\n", "\n")
                .replace("\\t", "")
                .replace("\\r\\n", "")
                .replace(".\"", ""),
            description = this.description.replace(".\"", ""),
        )
    }
}
