package screens.rules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.markdown.Markdown
import domain.models.Point
import domain.models.Rule
import ru.alexgladkov.odyssey.compose.local.LocalRootController

/**
 * Composable rules
 * @param rule - rule
 */
@Composable
fun RuleScreen(rule: Rule) {
    val navController = LocalRootController.current
    Column(
        Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${rule.number}. ${rule.title}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.weight(4f),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Назад")
            }
        }
        rule.points.forEach {
            ComposablePoints(it)
        }
    }
}

/**
 * Composable point from rule
 * @param point - rules element
 */
@Composable
fun ComposablePoints(point: Point) {
    val navController = LocalRootController.current
    Column {
        Spacer(modifier = Modifier.height(1.dp).background(Color.Black))
        Text(
            text = point.number,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 14.sp
        )
        Markdown(content = point.content)
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.weight(1f)
        ) {
            Text("Далі")
        }
    }
}
