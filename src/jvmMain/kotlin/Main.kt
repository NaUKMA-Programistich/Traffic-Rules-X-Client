
import navigation.NavGraph
import navigation.SetupNavigation
import ru.alexgladkov.odyssey.compose.setupNavigation
import javax.swing.JFrame
import javax.swing.SwingUtilities

/**
 * Entry point
 */
fun main() = SwingUtilities.invokeLater {
    val window = JFrame()
    window.title = "Traffic Rules X"
    window.setSize(600, 600)

    window.setupNavigation(NavGraph.Main.name) {
        SetupNavigation()
    }
}
