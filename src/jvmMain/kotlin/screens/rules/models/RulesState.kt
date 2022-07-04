package screens.rules.models

import domain.models.Rule

data class RulesState(
    val rules: List<Rule> = listOf()
)
