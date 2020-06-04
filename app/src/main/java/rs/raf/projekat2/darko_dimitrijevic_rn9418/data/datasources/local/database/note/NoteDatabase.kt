package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.note

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters()
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getDao() : NoteDao
}