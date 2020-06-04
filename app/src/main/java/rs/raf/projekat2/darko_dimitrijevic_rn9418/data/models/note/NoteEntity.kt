package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val content: String,
    val archived: Boolean
)