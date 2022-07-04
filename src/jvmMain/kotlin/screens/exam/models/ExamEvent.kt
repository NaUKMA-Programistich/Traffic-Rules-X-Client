package screens.exam.models

sealed class ExamEvent {
    object EntryDisplay : ExamEvent()
    object ExistTime : ExamEvent()
    data class Next(val variant: Int) : ExamEvent()
}
