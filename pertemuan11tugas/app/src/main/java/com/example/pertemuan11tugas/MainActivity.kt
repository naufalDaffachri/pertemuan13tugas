package com.example.pertemuan11tugas.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan11tugas.R
import com.example.pertemuan11tugas.adapter.UserAdapter
import com.example.pertemuan11tugas.databinding.ActivityMainBinding
import com.example.pertemuan11tugas.model.Data
import com.example.pertemuan11tugas.model.Users
import com.example.pertemuan11tugas.network.ApiClient
import com.example.pertemuan11tugas.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val apiService = ApiClient.getInstance()

        apiService.getUsers(2).enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    val userList = response.body()?.data ?: emptyList()
                    userAdapter = UserAdapter(userList)
                    binding.recyclerView.adapter = userAdapter
                } else {
                    Log.e("MainActivity", "Gagal memuat data: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.e("MainActivity", "Kesalahan jaringan: ${t.message}")
            }
        })
    }
}