package com.cs23_ps276.sahabatlansia.ui.notification

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.notification)
        mediaPlayer?.start()

        if (intent?.extras != null) {
            binding.title.text = intent?.getStringExtra("TITLE")
            binding.description.text = intent?.getStringExtra("DESC")
            binding.timeAndData.text = intent?.getStringExtra("DATE") + ", " + intent?.getStringExtra("TIME")
        }

        Glide.with(applicationContext).load(R.drawable.alert).into(binding.imageView)

        binding.closeButton.setOnClickListener { finish() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    companion object {
        private var inst: AlarmActivity? = null

        fun instance(): AlarmActivity? {
            return inst
        }
    }
}