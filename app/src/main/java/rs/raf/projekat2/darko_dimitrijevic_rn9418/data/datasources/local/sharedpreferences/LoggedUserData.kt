package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.datasources.local.sharedpreferences

import android.content.SharedPreferences
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.user.User
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.activities.LoginActivity

class LoggedUserData (private val sharedPreferences: SharedPreferences) {


    /**
     * This method login user and save data in shared preferences.
     *
     * @param user User object which data we want to store in shared preferences.
     * */
    fun login(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString(LoginActivity.USERNAME, user.username)
        editor.putString(LoginActivity.PIN, user.password)

        editor.apply()
    }


    /**
     * This method return currently logged user or null if there is no logged user.
     * */
    fun getLoggedUser() : User? {
        val username = sharedPreferences.getString(LoginActivity.USERNAME, "-1") ?: ""
        val password = sharedPreferences.getString(LoginActivity.PIN, "-1") ?: ""

        if (username != "-1" && password != "-1")
            return User(
                username,
                password
            )

        return null
    }

}