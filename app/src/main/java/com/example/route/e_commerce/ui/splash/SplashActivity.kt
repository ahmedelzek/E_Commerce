package com.example.route.e_commerce.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.route.e_commerce.R
import com.example.route.e_commerce.ui.auth.AuthActivity
import com.example.route.e_commerce.ui.main.MainActivity
import com.example.route.e_commerce.utils.UserDataFiled
import com.example.route.e_commerce.utils.UserDataUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        makeStatusBarTransparentAndIconsClear()
        Handler(Looper.getMainLooper())
            .postDelayed({
                if (!isAuthenticated()) {
                    startAuthActivity()
                } else {
                    startMainActivity()
                }
            }, 1200)
    }

    private fun isAuthenticated(): Boolean {
        return (UserDataUtils().getUserData(this, UserDataFiled.TOKEN) != null)
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun startAuthActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    private fun makeStatusBarTransparentAndIconsClear() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                )
        window.statusBarColor = Color.TRANSPARENT
    }
}
