package screens.exam.models

import domain.models.Question

data class ExamState(
    val id: Int = 0,
    val error: Int = 0,
    val question: Question? = null
)
