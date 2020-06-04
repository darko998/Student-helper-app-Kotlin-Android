package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts

import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.user.User

interface LoggedUserContract {

    interface LoggedUserViewModel {

        fun login(user: User)
        fun getUser(): User?
    }
}