package screens.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.markdown.Markdown
import domain.models.Question
import domain.models.ReasonExam
import domain.models.ResultExam
import navigation.NavGraph
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.components.ComposableImage

/*
    Result exam screen
 */
@Composable
fun ResultScreen(result: ResultExam) {
    val navController = LocalRootController.current
    val text = when (result.reasonExam) {
        ReasonExam.Error -> "Іспит не складено - 3 помилки"
        ReasonExam.God -> "Іспит складено, вітаємо - кількість помилок: ${result.questions.size}"
        ReasonExam.Time -> "Іспит не складено - вичерпано час"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderText(text)
            Button(
                onClick = { navController.push(NavGraph.Main.name) }
            ) {
                Text("На головну")
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color.Black))
        DescriptionQuestions(result.questions)
    }
}

@Composable
fun DescriptionQuestions(questions: List<Question>) {
    questions.forEach { question ->

        Text(
            text = question.title,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold
        )

        question.image?.let { ComposableImage(it) }

        question.variants.forEachIndexed { index, option ->
            var color = Color.White
            if (index == question.userId) color = Color.Red
            if (index == question.resultId - 1) color = Color.Green
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = (index == question.resultId - 1),
                    onClick = { },
                    enabled = true
                )
                Text(
                    text = option,
                    modifier = Modifier.background(color),
                    textAlign = TextAlign.Left
                )
            }
        }

        question.description?.let {
            Markdown(it)
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Black))
    }
}

@Composable
private fun HeaderText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
}
