package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note

sealed class ArchiveNoteState {
    object Success : ArchiveNoteState()
    data class Error(val error: String) : ArchiveNoteState()
}