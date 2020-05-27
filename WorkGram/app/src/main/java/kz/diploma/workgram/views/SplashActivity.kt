package kz.diploma.workgram.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kz.diploma.workgram.MainActivity
import kz.diploma.workgram.R
import kz.diploma.workgram.utils.Constants
import kz.diploma.workgram.views.auth.RegisterActivity
import kz.diploma.workgram.views.home.HomeActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private val sPref: SharedPreferences by inject()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val user = sPref.getString(Constants.USER_JSON, null)
        Handler().postDelayed({
            user?.let {
                if (!sPref.getString(Constants.USER_JSON, "").isNullOrEmpty()) {
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    startActivity(Intent(this, RegisterActivity::class.java))

                }
            } ?: run {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }, 800)
    }
}