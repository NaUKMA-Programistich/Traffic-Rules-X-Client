package domain.mappers

import data.dto.ExamDto
import data.dto.QuestionDto
import domain.models.Question

/*
    Convertor from exam dto to exam data
 */
object ExamMapper {

    /*
    Convertor from exam dto to list questions
    */
    fun ExamDto.mapper(): List<Question> {
        return this.questionList.map { it.mapper() }
    }

    /*
        Convertor from question dto to question
    */
    fun QuestionDto.mapper(): Question {
        return Question(
            image = pictureLink,
            title = name,
            variants = this.answers.map { it.name },
            resultId = correctId(this.answers.map { it.id }, this.correctAnswerId),
            description = ""
        )
    }

    /*
        From id answers to right index
     */
    private fun correctId(answers: List<Int>, correct: Int): Int {
        for (i in answers.indices) {
            if (answers[i] == correct) {
                return i + 1
            }
        }
        println("Answers: $answers")
        println("Correct: $correct")
        return -1
    }
}
