package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.LoggedUserContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.LoggedUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module


class SplashActivity : AppCompatActivity() {

    private val loggedUserViewModel : LoggedUserContract.LoggedUserViewModel by viewModel<LoggedUserViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent: Intent

        if(userIsLoggedIn()) {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        finish()
    }

    private fun userIsLoggedIn() : Boolean {

        return loggedUserViewModel.getUser() != null
    }
}