package com.example.pertemuan11tugas.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan11tugas.R
import com.example.pertemuan11tugas.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME")
        val email = intent.getStringExtra("EXTRA_EMAIL")

        binding.tvDetailName.text = "Name: $name"
        binding.tvDetailEmail.text = "Email: $email"
    }
}
