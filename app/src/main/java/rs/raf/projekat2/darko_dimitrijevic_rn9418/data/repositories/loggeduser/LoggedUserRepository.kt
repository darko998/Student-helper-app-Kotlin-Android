package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.loggeduser

import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.user.User

interface LoggedUserRepository {

    fun login(user: User)
    fun getLoggedUser() : User?

}