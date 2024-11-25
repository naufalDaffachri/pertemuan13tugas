package com.example.pertemuan13tugas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan13tugas.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("NAME")
        val nik = intent.getStringExtra("NIK")
        val gender = intent.getStringExtra("GENDER")
        val address = intent.getStringExtra("ADDRESS")

        binding.detailName.setText("Nama: $name")
        binding.detailNik.setText("NIK: $nik")
        binding.detailGenderEditText.setText("Gender: $gender")
        binding.detailAddress.setText("Alamat: $address")

        binding.backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
