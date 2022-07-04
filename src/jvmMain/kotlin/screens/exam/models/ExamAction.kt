package screens.exam.models

import domain.models.ResultExam

sealed class ExamAction {
    data class Navigate(val result: ResultExam) : ExamAction()
}
