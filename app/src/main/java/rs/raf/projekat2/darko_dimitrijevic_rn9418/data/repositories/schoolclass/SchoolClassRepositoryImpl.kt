package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.schoolclass

import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.database.SchoolClassDao
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.remote.SchoolClassService
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClassEntity
import timber.log.Timber

class SchoolClassRepositoryImpl(private val remoteDataSource: SchoolClassService, private val localDataSource: SchoolClassDao) : SchoolClassRepository {


    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource.getAll()
            .doOnNext {

                Timber.e("Downloading data from net: " + it.toString())
                var entities = it.map {
                    SchoolClassEntity (
                        id = 0,
                        name = it.predmet,
                        type = it.tip,
                        professor = it.nastavnik,
                        classroom = it.ucionica,
                        groups = it.grupe,
                        day = it.dan,
                        term = it.termin
                    )
                }

                localDataSource.deleteAndInsertAll(entities)

            }.map {
                Resource.Success(Unit)
            }
    }

}