package com.dicoding.picodiploma.loginwithanimation.view.welcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivityWelcomeBinding
import com.dicoding.picodiploma.loginwithanimation.view.login.LoginActivity
import com.dicoding.picodiploma.loginwithanimation.view.signup.SignupActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }


    //animas
    private fun playAnimation() {
        //animasi untuk gambar
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply { //Translation_X = Animasi secara horizontal dari kiri ke kanan
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE // supaya animasi terus berjalan
            repeatMode = ObjectAnimator.REVERSE // supaya animasi bisa kemabali ke kiri
        }.start()

        //animasi fade in (ALPHA)
        //Ingat untuk mengatur alpha = 0 dan tools:alpha = 100 di layout xml
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(1000)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(1000)
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(1000)
        val desc = ObjectAnimator.ofFloat(binding.descTextView, View.ALPHA, 1f).setDuration(1000)

        //menjalankan animasi secara bersamaan (tombol lodin dan signup)
        val together = AnimatorSet().apply {
            playTogether(login, signup)
        }

        //menjalankan animasi secara berurutan
        AnimatorSet().apply {
            playSequentially(title, desc, together)
            start()
        }

        //Cara alternatif (lebih rumit)
//        AnimatorSet().apply {
//            play(login).with(signup)
//            play(login).after(desc)
//            play(title).before(desc)
//            start()
//        }

        //playTogether() : Menjalankan animasi secara bersama-sama.
        //playSequentially() : Menjalankan animasi secara bergantian.
        //play().with() : Menjalankan 2 animasi secara bersamaan.
        //play().before() : Menjalankan animasi a sebelum animasi b.
        //play().after() : Menjalankan animasi a setelah animasi b.
    }
}