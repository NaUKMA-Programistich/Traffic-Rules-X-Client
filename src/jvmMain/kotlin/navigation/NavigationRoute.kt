package navigation

import domain.models.ResultExam
import domain.models.Rule
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import screens.MainScreen
import screens.exam.ExamScreen
import screens.result.ResultScreen
import screens.rules.RuleScreen
import screens.rules.RulesScreen

/*
    Navigation router
 */
fun RootComposeBuilder.SetupNavigation() {
    screen(NavGraph.Main.name) {
        MainScreen()
    }
    screen(NavGraph.Exam.name) {
        ExamScreen()
    }
    screen(NavGraph.Result.name) {
        ResultScreen(it as ResultExam)
    }
    screen(NavGraph.Rules.name) {
        RulesScreen()
    }
    screen(NavGraph.Rule.name) {
        RuleScreen(it as Rule)
    }
}
