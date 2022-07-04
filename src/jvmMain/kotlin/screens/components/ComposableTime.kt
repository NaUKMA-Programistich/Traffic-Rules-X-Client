package screens.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import screens.exam.TIME_EXAM

/*
    Timer
 */
@Composable
fun ComposableTime(timeInSeconds: Int) {
    val minutes: Int = timeInSeconds / 60
    val seconds = timeInSeconds - minutes * 60
    val minStr = if (minutes < 10) "0$minutes" else minutes
    val secStr = if (seconds < 10) "0$seconds" else seconds
    val color = if (timeInSeconds > TIME_EXAM - 60) Color.Red else Color.Black
    Text(
        text = "Час: $minStr:$secStr",
        color = color
    )
}
