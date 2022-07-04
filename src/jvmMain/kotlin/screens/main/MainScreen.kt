package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.NavGraph
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

/*
    Main screen with navigation
 */
@Composable
fun MainScreen() {
    val navController = LocalRootController.current
    Column(
        modifier = Modifier.fillMaxSize().padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ComposableChoose(
                text = "Правила\nдорожнього руху",
                description = "Перелік тем з правил дорожнього руху\nСтандарт 2022 року"
            ) { navController.push(NavGraph.Rules.name) }
            ComposableChoose(
                text = "Перевірка знань\nу МВС",
                description = "20 хвилини та 20 завдань - теоритичний залік.\nМаксимум дві помилки"
            ) { navController.push(NavGraph.Exam.name) }
        }
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontFamily = FontFamily.Monospace, fontSize = 16.sp)) {
                    append("Розроблено командою ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("#define Failure us")
                    }
                    append("\nby Джос Олексій та Загривий Олег")
                }
            },
            textAlign = TextAlign.Center,
        )
    }
}

/*
    Choose variants
 */
@Composable
private fun ComposableChoose(
    text: String,
    description: String,
    action: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = action, modifier = Modifier.weight(2f)) {
            Text(text = text, textAlign = TextAlign.Center)
        }
        Text(description, modifier = Modifier.weight(3f))
    }
}
