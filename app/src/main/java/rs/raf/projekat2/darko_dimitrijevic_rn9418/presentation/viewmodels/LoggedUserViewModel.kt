package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels

import androidx.lifecycle.ViewModel
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.User
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.repositories.loggeduser.LoggedUserRepository
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.LoggedUserContract

class LoggedUserViewModel (private val loggedUserRepository: LoggedUserRepository) : ViewModel(), LoggedUserContract.LoggedUserViewModel {


    override fun login(user: User) {
        loggedUserRepository.login(user)
    }

    override fun getUser() : User? {
        return loggedUserRepository.getLoggedUser()
    }

}