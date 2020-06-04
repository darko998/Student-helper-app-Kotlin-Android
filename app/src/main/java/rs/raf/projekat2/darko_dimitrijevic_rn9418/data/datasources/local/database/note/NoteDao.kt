package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.note

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.Note
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.NoteEntity

@Dao
abstract class NoteDao {

    @Insert
    abstract fun insert(note: NoteEntity) : Completable

    @Query("SELECT * FROM notes WHERE archived = :archived")
    abstract fun getAll(archived: Boolean) : Observable<List<Note>>

    @Query("SELECT * FROM notes WHERE (title LIKE '%' || :title || '%' OR content LIKE '%' || :content || '%') AND archived = :archived")
    abstract fun getByTitleOrContent(title: String, content: String, archived: Boolean) : Observable<List<Note>>

    @Query("DELETE FROM notes WHERE id = :id")
    abstract fun delete(id: Long) : Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(note: NoteEntity) : Completable



}