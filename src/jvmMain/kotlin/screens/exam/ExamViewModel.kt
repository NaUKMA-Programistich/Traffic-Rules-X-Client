package screens.exam

import com.adeo.kviewmodel.BaseSharedViewModel
import data.HttpClient
import domain.mappers.ExamMapper.mapper
import domain.models.Question
import domain.models.ReasonExam
import domain.models.ResultExam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import screens.exam.models.ExamAction
import screens.exam.models.ExamEvent
import screens.exam.models.ExamState

/*
    ViewModel for logic from exam screen
 */
class ExamViewModel : BaseSharedViewModel<ExamState, ExamAction, ExamEvent>(
    initialState = ExamState()
) {
    private val questions = mutableListOf<Question>()
    private val errorsQuestions = mutableListOf<Question>()
    private val httpClient = HttpClient()

    /*
        Entry point for events
     */
    override fun obtainEvent(viewEvent: ExamEvent) {
        when (viewEvent) {
            ExamEvent.EntryDisplay -> loadingContent()
            ExamEvent.ExistTime -> timeDone()
            is ExamEvent.Next -> processAnswer(viewEvent.variant)
        }
    }

    /*
        Time exist
     */
    private fun timeDone() {
        viewAction = navigate(ReasonExam.Time)
    }

    /*
        Navigate after some reason
    */
    private fun navigate(reasonExam: ReasonExam): ExamAction.Navigate {
        val result = ResultExam(
            questions = errorsQuestions,
            reasonExam = reasonExam
        )
        return ExamAction.Navigate(result)
    }

    /*
        process next question
    */
    private fun processAnswer(id: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val current = viewState.question!!

            viewState = viewState.copy(
                question = current.copy(
                    userId = id
                )
            )

            if (id != current.resultId - 1) {
                viewState = viewState.copy(
                    error = viewState.error + 1
                )
                errorsQuestions.add(viewState.question!!)
            }

            delay(250)

            if (viewState.error == 3) {
                viewAction = navigate(ReasonExam.Error)
            } else if (viewState.id == questions.size) {
                viewAction = navigate(ReasonExam.God)
            } else {
                nextQuestion()
            }
        }
    }

    /*
        First loading
     */
    private fun loadingContent() {
        viewModelScope.launch(Dispatchers.Default) {
            val questionsHttp = httpClient.getExam().mapper()
            println(questionsHttp)
            questions.addAll(questionsHttp)
            nextQuestion()
        }
    }

    /*
        Next question
     */
    private fun nextQuestion() {
        val nextId = viewState.id + 1
        viewState = viewState.copy(
            id = nextId,
            question = questions[nextId - 1]
        )
    }
}
