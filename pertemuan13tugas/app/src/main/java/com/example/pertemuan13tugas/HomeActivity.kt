package com.example.pertemuan13tugas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.pertemuan13tugas.databinding.ActivityHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var db: VoterDatabase
    private lateinit var adapter: VoterAdapter
    private val voterList = mutableListOf<Voter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, VoterDatabase::class.java, "voter-db")
            .build()

        adapter = VoterAdapter(voterList, onEditClick = { voter ->
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("ID", voter.id)
            intent.putExtra("NAME", voter.name)
            intent.putExtra("NIK", voter.nik)
            intent.putExtra("GENDER", voter.gender)
            intent.putExtra("ADDRESS", voter.address)
            startActivityForResult(intent, EDIT_REQUEST_CODE)
        }, onDetailClick = { voter ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("NAME", voter.name)
            intent.putExtra("NIK", voter.nik)
            intent.putExtra("GENDER", voter.gender)
            intent.putExtra("ADDRESS", voter.address)
            startActivity(intent)
        })

        binding.voterRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.voterRecyclerView.adapter = adapter

        binding.addDataButton.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        loadVoters()

        checkLoginStatus()

        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun loadVoters() {
        GlobalScope.launch(Dispatchers.Main) {
            val voters = db.voterDao().getAllVoters()
            voterList.clear()
            voterList.addAll(voters)
            adapter.notifyDataSetChanged()
        }
    }

    private fun checkLoginStatus() {
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)

        if (isLoggedIn) {
            Toast.makeText(this, "Berhasil login!", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun logout() {
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("is_logged_in", false)
        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadVoters()
        }
    }

    companion object {
        const val EDIT_REQUEST_CODE = 1
    }
}
