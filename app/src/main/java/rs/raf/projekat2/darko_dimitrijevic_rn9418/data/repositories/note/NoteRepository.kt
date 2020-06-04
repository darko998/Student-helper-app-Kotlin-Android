package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.note

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note

interface NoteRepository {

    fun insert(note: Note) : Completable
    fun getAll(archived: Boolean) : Observable<Resource<List<Note>>>
    fun getByTitleOrContent(title: String, content: String, archived: Boolean) : Observable<Resource<List<Note>>>
    fun delete(note: Note) : Completable
    fun changeArchiveState(note: Note) : Completable
}