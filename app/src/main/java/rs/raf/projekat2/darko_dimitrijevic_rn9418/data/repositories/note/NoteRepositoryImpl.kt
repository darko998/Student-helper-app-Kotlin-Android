package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.note

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.note.NoteDao
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.NoteEntity

class NoteRepositoryImpl (val dataSource: NoteDao) : NoteRepository {

    override fun insert(note: Note): Completable {
        val noteEntity = NoteEntity(note.id, note.title, note.content, false)

        return dataSource.insert(noteEntity)
    }

    override fun getAll(archived: Boolean): Observable<Resource<List<Note>>> {
        return dataSource
            .getAll(archived)
            .map {
                Resource.Success(it)
            }
    }

    override fun getByTitleOrContent(title: String, content: String, archived: Boolean): Observable<Resource<List<Note>>> {
        return dataSource
            .getByTitleOrContent(title, content, archived)
            .map {
                Resource.Success(it)
            }
    }

    override fun delete(note: Note): Completable {
        return dataSource.delete(note.id)
    }

    override fun changeArchiveState(note: Note): Completable {

        /** If note is archived now we want to unarchive, if note is not archived, now we want to
         * archive it. Because that we always get opposite state of note. */
        val oppositeStateArchive = !note.archived

        return dataSource.changeArchiveState(note.id, oppositeStateArchive)
    }


}