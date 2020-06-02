package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.User

interface LoggedUserContract {

    interface LoggedUserViewModel {

        fun login(user: User)
        fun getUser(): User?
    }
}