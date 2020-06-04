package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note

sealed class FetchedNotesCountListState {
    data class Success(val data: List<Int>) : FetchedNotesCountListState()
    data class Error(val error: String) : FetchedNotesCountListState()
}