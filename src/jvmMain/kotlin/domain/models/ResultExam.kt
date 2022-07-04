package domain.models

/*
    Exam result
 */
data class ResultExam(
    val questions: List<Question>,
    val reasonExam: ReasonExam
)
