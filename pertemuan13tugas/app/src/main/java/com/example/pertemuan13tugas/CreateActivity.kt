package com.example.pertemuan13tugas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.pertemuan13tugas.databinding.ActivityCreateBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private lateinit var db: VoterDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, VoterDatabase::class.java, "voter-db")
            .build()

        binding.addSaveButton.setOnClickListener {
            val name = binding.addNameEditText.text.toString().trim()
            val nik = binding.addNikEditText.text.toString().trim()
            val gender = when (binding.addGenderRadioGroup.checkedRadioButtonId) {
                R.id.addMaleRadioButton -> "Laki-Laki"
                R.id.addFemaleRadioButton -> "Perempuan"
                else -> ""
            }
            val address = binding.addAddressEditText.text.toString().trim()

            if (name.isEmpty() || nik.isEmpty() || gender.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newVoter = Voter(name = name, nik = nik, gender = gender, address = address)
            GlobalScope.launch(Dispatchers.Main) {
                db.voterDao().insertVoter(newVoter)
                Toast.makeText(this@CreateActivity, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@CreateActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
