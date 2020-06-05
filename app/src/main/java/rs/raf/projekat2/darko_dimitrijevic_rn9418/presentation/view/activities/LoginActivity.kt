package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.user.User
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.contracts.LoggedUserContract
import rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.viewmodels.LoggedUserViewModel

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private val loggedUserViewModel : LoggedUserContract.LoggedUserViewModel by viewModel<LoggedUserViewModel>()

    companion object {
        const val USERNAME = "username"
        const val PIN = "password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        login_btn.setOnClickListener {
            val username = login_username.text.toString()
            val pin = login_pin.text.toString()

            if(pin != "1234"){
                /** If pin is incorrect notify user. */
                Toast.makeText(this, "Pogresan pin!", Toast.LENGTH_SHORT);
            } else {
                /** If pin is correct login user and save username and pin in shared preferences. */
                loggedUserViewModel.login(
                    User(
                        username,
                        pin
                    )
                )

                val intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}