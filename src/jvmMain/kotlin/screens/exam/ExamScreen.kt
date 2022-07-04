package screens.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import domain.models.Question
import kotlinx.coroutines.delay
import navigation.NavGraph
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.components.ComposableImage
import screens.components.ComposableInProgress
import screens.components.ComposableTime
import screens.exam.models.ExamAction
import screens.exam.models.ExamEvent

const val TIME_EXAM = 1200

/*
    Exam screen
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExamScreen() {
    StoredViewModel({ ExamViewModel() }) { viewModel ->
        var isOpen by mutableStateOf(false)
        val navController = LocalRootController.current
        val viewAction by viewModel.viewActions().observeAsState()
        val viewState by viewModel.viewStates().observeAsState()

        var times by remember { mutableStateOf(0) }
        var timerExist by remember { mutableStateOf(true) }
        LaunchedEffect(key1 = true) {
            while (timerExist) {
                delay(1000)
                times++
                if (times >= TIME_EXAM) {
                    timerExist = false
                    viewModel.obtainEvent(ExamEvent.ExistTime)
                }
            }
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { isOpen = true }
                ) {
                    Icon(Icons.Filled.Home, "")
                }
            }
        ) {
            if (isOpen) {
                AlertDialog(
                    onDismissRequest = { },
                    title = {
                        Text(
                            text = "Ви впевнені що хочете завершити тест?",
                            textAlign = TextAlign.Center
                        )
                    },
                    buttons = {
                        Column(
                            modifier = Modifier.padding(all = 8.dp).width(200.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    isOpen = false
                                    navController.popBackStack()
                                }
                            ) {
                                Text("Так")
                            }
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { isOpen = false }
                            ) {
                                Text("Ні")
                            }
                        }
                    }
                )
            }
            if (viewState.question == null) {
                ComposableInProgress()
            } else {
                Column(
                    modifier = Modifier.fillMaxSize().padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Кількість помилок: ${viewState.error}")
                        Text("Запитання: ${viewState.id}/20")
                        ComposableTime(times)
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color.Black))
                    ComposableQuestion(
                        viewState.question!!,
                        onNext = { viewModel.obtainEvent(ExamEvent.Next(it)) }
                    )
                }
            }
        }

        LaunchedEffect(key1 = viewAction) {
            val localViewAction = viewAction
            when (localViewAction) {
                is ExamAction.Navigate -> {
                    timerExist = false
                    navController.push(NavGraph.Result.name, localViewAction.result)
                }
                else -> {}
            }
        }

        LaunchedEffect(key1 = true) {
            viewModel.obtainEvent(ExamEvent.EntryDisplay)
        }
    }
}

/*
    Question screen
 */
@Composable
private fun ComposableQuestion(
    question: Question,
    onNext: (Int) -> Unit
) {
    var chooseVariant by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = question.title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))

        question.image?.let { ComposableImage(it) }

        question.variants.forEachIndexed { index, option ->
            var color = Color.White
            if (question.userId != -1) {
                if (index == question.resultId - 1) color = Color.Green
                else if (index == question.userId) color = Color.Red
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { chooseVariant = index }
            ) {
                RadioButton(
                    selected = (index == chooseVariant && chooseVariant != -1) || (index == question.userId),
                    onClick = { chooseVariant = index }
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(8.dp).background(color)

                )
            }
        }

        Button(
            onClick = {
                onNext.invoke(chooseVariant)
                chooseVariant = -1
            },
            enabled = chooseVariant != -1
        ) {
            Text("Наступне")
        }
    }
}
