package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {


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
    }

    fun userIsLoggedIn() : Boolean {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE);
        val user = sharedPreferences.getString(LoginActivity.USERNAME, "-1")

        return user != "-1"
    }
}