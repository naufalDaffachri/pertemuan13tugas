package com.example.pertemuan10

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pertemuan10.databinding.ActivityMainBinding
import com.example.pertemuan10.databinding.ItemDisasterBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val disasterAdapter = DisasterAdapter(generateDummy()) {disaster ->
            Toast.makeText(this, "selected: ${disaster.nameDisaster}", Toast.LENGTH_LONG).show()
        }

        with(binding) {
            rvDisaster.apply {
                adapter = disasterAdapter
                //layoutManager = LinearLayoutManager(this@MainActivity) *bisa pake ini juga //
                layoutManager = GridLayoutManager(this@MainActivity, 2)
            }
        }

    }
    private fun generateDummy(): List<Disaster> {
        return listOf(
            Disaster(nameDisaster = "Tsunami", disasterType = "Natural"),
            Disaster(nameDisaster = "Volcanic Eruption", disasterType =
            "Natural"),
            Disaster(nameDisaster = "Earthquake", disasterType = "Natural"),
            Disaster(nameDisaster = "Flood", disasterType = "Natural"),
            Disaster(nameDisaster = "Fire", disasterType = "Natural"),
            Disaster(nameDisaster = "Nuclear Accident", disasterType = "Manmade"),
            Disaster(nameDisaster = "Terrorist Attack", disasterType = "Manmade"),
            Disaster(nameDisaster = "War", disasterType = "Man-made")
        )
    }
}