package screens.rules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import domain.models.Rule
import navigation.NavGraph
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.components.ComposableInProgress
import screens.rules.models.RulesEvent

/**
 * Rules Screen
 */
@Composable
fun RulesScreen() {
    StoredViewModel({ RulesViewModel() }) { viewModel ->
        val navController = LocalRootController.current
        val viewState by viewModel.viewStates().observeAsState()

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(Icons.Filled.Home, "")
                }
            }
        ) {
            if (viewState.rules.isEmpty()) {
                ComposableInProgress()
            } else {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    viewState.rules.forEach {
                        ComposableRules(it) { navController.push(NavGraph.Rule.name, it) }
                    }
                }
            }

            LaunchedEffect(key1 = true) {
                viewModel.obtainEvent(RulesEvent.ObtainDisplay)
            }
        }
    }
}

/**
 * Composable rules screen
 * @param rule - rule
 * @param push - action on click
 */
@Composable
fun ComposableRules(rule: Rule, push: (Rule) -> Unit) {
    Button(
        onClick = { push.invoke(rule) },
        modifier = Modifier.background(Color.White).fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(alpha = 0.3f))
    ) {
        Text(
            text = "${rule.number}. ${rule.title}",
            modifier = Modifier.padding(horizontal = 12.dp),
            textAlign = TextAlign.Center
        )
    }
}
