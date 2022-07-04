package screens.rules

import com.adeo.kviewmodel.BaseSharedViewModel
import data.HttpClient
import domain.mappers.RulesMapper.mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import screens.rules.models.RulesEvent
import screens.rules.models.RulesState

/*
    ViewModel for logic from rules screen
 */
class RulesViewModel : BaseSharedViewModel<RulesState, Unit, RulesEvent>(
    initialState = RulesState()
) {
    private val httpClient = HttpClient()

    /*
        Entry point for events
     */
    override fun obtainEvent(viewEvent: RulesEvent) {
        when (viewEvent) {
            is RulesEvent.ObtainDisplay -> process()
        }
    }

    /*
        Get rules from server
     */
    private fun process() {
        viewModelScope.launch(Dispatchers.Default) {
            val rules = httpClient.getRules().mapper()
            viewState = viewState.copy(
                rules = rules
            )
        }
    }
}
