package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.loggeduser

import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.sharedpreferences.LoggedUserData
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.user.User

class LoggedUserRepositoryImpl(private val loggedUserDataSource: LoggedUserData) : LoggedUserRepository {

    override fun login(user: User) {
        loggedUserDataSource.login(user)
    }

    override fun getLoggedUser(): User? {
        return loggedUserDataSource.getLoggedUser()
    }

}