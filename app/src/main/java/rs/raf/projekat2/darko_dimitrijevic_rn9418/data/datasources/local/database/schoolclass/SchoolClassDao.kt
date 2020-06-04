package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.schoolclass

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClass
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

    @Query("SELECT * FROM school_classes")
    abstract fun getAll() : Observable<List<SchoolClass>>

    @Query("SELECT * FROM school_classes WHERE name LIKE '%' || :name || '%'")
    abstract fun getByName(name: String) : Observable<List<SchoolClass>>

    @Query("SELECT * FROM school_classes WHERE (name LIKE '%' || :name || '%' OR professor LIKE '%' || :professor || '%') AND groups LIKE '%' || :group || '%' AND day LIKE '%' || :day || '%'")
    abstract fun getFiltered(name: String, professor: String, group: String, day: String) : Observable<List<SchoolClass>>
}