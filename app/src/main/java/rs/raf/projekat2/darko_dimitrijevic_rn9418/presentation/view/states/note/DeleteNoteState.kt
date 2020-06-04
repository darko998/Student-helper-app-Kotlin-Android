package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note

sealed class DeleteNoteState {
    object Success : DeleteNoteState()
    data class Error(val error: String) : DeleteNoteState()
}