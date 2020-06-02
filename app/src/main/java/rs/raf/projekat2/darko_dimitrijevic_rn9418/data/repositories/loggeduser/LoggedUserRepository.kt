package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.loggeduser

import io.reactivex.Observable
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.User

interface LoggedUserRepository {

    fun login(user: User)
    fun getLoggedUser() : User?

}