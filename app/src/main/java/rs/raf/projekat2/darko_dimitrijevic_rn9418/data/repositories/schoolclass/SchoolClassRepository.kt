package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.schoolclass

import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClass

interface SchoolClassRepository {

    fun fetchAll() : Observable<Resource<Unit>>
    fun getAll() : Observable<Resource<List<SchoolClass>>>
    fun getByName(name: String) : Observable<Resource<List<SchoolClass>>>
    fun getFiltered(name: String, professor: String, group: String, day: String) : Observable<Resource<List<SchoolClass>>>
}