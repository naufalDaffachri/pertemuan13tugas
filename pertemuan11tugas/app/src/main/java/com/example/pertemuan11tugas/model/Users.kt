package com.example.pertemuan11tugas.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("data")
    val `data`: List<Data>
)
