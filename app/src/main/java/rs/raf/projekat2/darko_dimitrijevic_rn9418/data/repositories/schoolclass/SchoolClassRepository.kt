package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.schoolclass

import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.Resource

interface SchoolClassRepository {

    fun fetchAll() : Observable<Resource<Unit>>
}