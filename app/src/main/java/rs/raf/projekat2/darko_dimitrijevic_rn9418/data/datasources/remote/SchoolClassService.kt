package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClass
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.schoolclass.SchoolClassResponse


interface SchoolClassService {

    @GET("raspored/json.php/")
    fun getAll() : Observable<List<SchoolClassResponse>>
}