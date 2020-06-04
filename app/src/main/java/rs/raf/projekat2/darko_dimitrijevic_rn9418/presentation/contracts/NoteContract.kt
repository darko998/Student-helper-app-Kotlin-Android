package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.states.note.*

interface NoteContract {

    interface ViewModel {
        val addDone: LiveData<AddNoteState>
        val deleteDone: LiveData<DeleteNoteState>
        val editDone: LiveData<EditNoteState>
        val archiveDone: LiveData<ArchiveNoteState>
        val notesState: LiveData<NotesState>

        fun insert(note: Note)
        fun getAll(archived: Boolean)
        fun getByTitleOrContent(title: String, content: String, archived: Boolean)
        fun delete(note: Note)
        fun changeArchiveState(note: Note)
    }
}