package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database

import androidx.room.*
import io.reactivex.Completable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClassEntity

@Dao
abstract class SchoolClassDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<SchoolClassEntity>) : Completable

    @Query("DELETE FROM school_classes")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<SchoolClassEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
}