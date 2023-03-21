package ru.aston.sarancha_aston_course_project.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.aston.sarancha_aston_course_project.MainActivity
import ru.aston.sarancha_aston_course_project.databinding.ActivitySplashScreenBinding
import ru.aston.sarancha_aston_course_project.utils.SPLASH_DELAY
import ru.aston.sarancha_aston_course_project.view.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashScreenBinding>() {

    private val handler = Handler(Looper.getMainLooper())

    override fun getViewBinding() = ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashAnimation()
    }

    private fun splashAnimation() {

        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_DELAY)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}