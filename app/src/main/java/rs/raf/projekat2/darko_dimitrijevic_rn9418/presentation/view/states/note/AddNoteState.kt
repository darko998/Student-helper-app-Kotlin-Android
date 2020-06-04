package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note

sealed class AddNoteState {
    object Success : AddNoteState()
    data class Error(val error: String) : AddNoteState()
}