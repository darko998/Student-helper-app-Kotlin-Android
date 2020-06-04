package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.schoolclass

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.schoolclass.SchoolClassDao
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClassEntity

@Database (
    entities = [SchoolClassEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters()
abstract class SchoolClassDatabase : RoomDatabase() {
    abstract fun getSchoolClassDao(): SchoolClassDao
}