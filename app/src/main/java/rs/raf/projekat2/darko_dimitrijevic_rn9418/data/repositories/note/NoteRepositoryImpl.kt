package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.note

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.note.NoteDao
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.DateRange
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.NoteEntity
import timber.log.Timber
import java.util.*

class NoteRepositoryImpl (val dataSource: NoteDao) : NoteRepository {

    override fun insert(note: Note): Completable {
        val noteEntity = NoteEntity(note.id, note.title, note.content, false, note.created)

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

        return dataSource.update(NoteEntity(note.id, note.title, note.content, oppositeStateArchive, note.created))
    }

    override fun update(note: Note): Completable {
        return dataSource.update(NoteEntity(note.id, note.title, note.content, note.archived, note.created))
    }

    override fun getNotesCountForLast5Days(): Observable<Resource<List<Int>>> {
        val timeFrom = Date().time - 432000000
        val notes : MutableList<Note> = mutableListOf()

        return dataSource.getNotesInLast5Days(timeFrom).map {

            Resource.Success(makeCountList(it))
        }

    }

    private fun makeCountList(notes: List<Note>) : List<Int> {
        val day5 = Date(Date().time - 432000000).date
        val day4 = Date(Date().time - 345600000).date
        val day3 = Date(Date().time - 259200000).date
        val day2 = Date(Date().time - 172800000).date
        val day1 = Date(Date().time - 86400000).date

        var notesCountList = mutableListOf(0, 0, 0, 0, 0)


        for (note in notes) {
            if (Date(note.created).date == day5)
                notesCountList[0] = notesCountList[0] + 1

            if (Date(note.created).date == day4)
                notesCountList[1] = notesCountList[1] + 1

            if (Date(note.created).date == day3)
                notesCountList[2] = notesCountList[2] + 1

            if (Date(note.created).date == day2)
                notesCountList[3] = notesCountList[3] + 1

            if (Date(note.created).date == day1)
                notesCountList[4] = notesCountList[4] + 1
        }

        return notesCountList
    }

}