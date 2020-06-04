package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note

sealed class EditNoteState {
    object Success : EditNoteState()
    data class Error(val error: String) : EditNoteState()
}