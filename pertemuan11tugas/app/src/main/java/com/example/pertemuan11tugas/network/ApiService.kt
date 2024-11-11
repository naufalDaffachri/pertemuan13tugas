package com.example.pertemuan11tugas.network

import com.example.pertemuan11tugas.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<Users>
}