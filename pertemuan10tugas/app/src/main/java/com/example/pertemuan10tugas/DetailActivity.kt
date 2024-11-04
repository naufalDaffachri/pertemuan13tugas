package com.example.pertemuan10tugas

// DetailActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan10tugas.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sender = intent.getStringExtra("email_sender")
        val subject = intent.getStringExtra("email_subject")
        val body = intent.getStringExtra("email_body")

        binding.tvDetailSender.text = sender
        binding.tvDetailSubject.text = subject
        binding.tvDetailBody.text = body
    }
}
