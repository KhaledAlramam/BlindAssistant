package com.sedra.blindassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch{
            delay(2000)
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}