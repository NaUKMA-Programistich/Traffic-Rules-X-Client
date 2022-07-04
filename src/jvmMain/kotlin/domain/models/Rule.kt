package domain.models

/*
    Rule
 */
data class Rule(
    val number: String,
    val title: String,
    val points: List<Point>
)
