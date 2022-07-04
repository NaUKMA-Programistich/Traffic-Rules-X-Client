package domain.models

/*
    Question
 */
data class Question(
    val image: String? = null,
    val title: String,
    val variants: List<String>,
    val resultId: Int,
    val userId: Int = -1,
    val description: String?,
)
