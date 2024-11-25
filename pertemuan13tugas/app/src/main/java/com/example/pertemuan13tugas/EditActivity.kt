package com.example.pertemuan13tugas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.pertemuan13tugas.databinding.ActivityEditBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var db: VoterDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, VoterDatabase::class.java, "voter-db")
            .build()

        val voterId = intent.getIntExtra("ID", 0)
        val name = intent.getStringExtra("NAME") ?: ""
        val nik = intent.getStringExtra("NIK") ?: ""
        val gender = intent.getStringExtra("GENDER") ?: ""
        val address = intent.getStringExtra("ADDRESS") ?: ""

        binding.editNameEditText.setText(name)
        binding.editNikEditText.setText(nik)
        binding.editGenderRadioGroup.check(
            if (gender == "Laki-Laki") R.id.editMaleRadioButton else R.id.editFemaleRadioButton
        )
        binding.editAddressEditText.setText(address)

        binding.editSaveButton.setOnClickListener {
            val updatedName = binding.editNameEditText.text.toString().trim()
            val updatedNik = binding.editNikEditText.text.toString().trim()
            val updatedGender = when (binding.editGenderRadioGroup.checkedRadioButtonId) {
                R.id.editMaleRadioButton -> "Laki-Laki"
                R.id.editFemaleRadioButton -> "Perempuan"
                else -> ""
            }
            val updatedAddress = binding.editAddressEditText.text.toString().trim()

            if (updatedName.isEmpty() || updatedNik.isEmpty() || updatedGender.isEmpty() || updatedAddress.isEmpty()) {
                Toast.makeText(this, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedVoter = Voter(id = voterId, name = updatedName, nik = updatedNik, gender = updatedGender, address = updatedAddress)
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    db.voterDao().updateVoter(updatedVoter)
                    Toast.makeText(this@EditActivity, "Data berhasil diperbarui!", Toast.LENGTH_SHORT).show()
                    setResult(Activity.RESULT_OK)
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@EditActivity, "Gagal memperbarui data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
