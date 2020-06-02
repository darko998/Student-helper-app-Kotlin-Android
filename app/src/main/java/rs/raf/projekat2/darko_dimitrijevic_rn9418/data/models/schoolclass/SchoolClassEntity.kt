package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "school_classes")
data class SchoolClassEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val type: String,
    val professor: String,
    val classroom: String,
    val groups: String,
    val day: String,
    val term: String
)