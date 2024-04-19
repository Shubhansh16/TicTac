package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.databinding.ActivityStartActiivityBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartActiivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStartActiivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playBtn.setOnClickListener {
            startActivity(Intent(this@StartActivity,MainActivity::class.java))
            finish()
        }
    }
}