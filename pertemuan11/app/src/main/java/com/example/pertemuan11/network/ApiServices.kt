package com.example.pertemuan11.network

import com.example.pertemuan11.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("users?page=2") //ini ikut di figma
    fun getAllUsers(): Call<Users>
}