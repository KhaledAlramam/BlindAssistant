package com.sedra.blindassistant

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer
    private lateinit var gestureDetector: GestureDetector
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(this, R.raw.welcome)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.start)
        mediaPlayer.setOnCompletionListener {
            mediaPlayer2.start()
        }
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onSingleTapUp(e: MotionEvent): Boolean {
                clickCount++
                if (clickCount == 3) {
                    clickCount = 0
                    startActivity(Intent(this@MainActivity, CameraActivity::class.java))
                }
                return super.onSingleTapUp(e)
            }

        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onStop() {
        super.onStop()
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        if (mediaPlayer2.isPlaying) mediaPlayer2.stop()
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        mediaPlayer2.release()
    }

}