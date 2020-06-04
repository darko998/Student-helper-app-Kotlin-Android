package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note

import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note

sealed class NotesState {
    object Loading : NotesState()
    data class Success(val notes: List<Note>) : NotesState()
    data class SuccessFilteredData(val notes: List<Note>) : NotesState()
    data class Error(val error: String) : NotesState()

}