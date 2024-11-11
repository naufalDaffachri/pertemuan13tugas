package com.example.pertemuan11.model

import com.google.gson.annotations.SerializedName

//semua kolomnya disesuain sama yang di figma
data class Data(@SerializedName("id")
                val id: Int,

                @SerializedName("email")
                val employeeSalary: String,

                @SerializedName("first_name")
                val employeeName: String,

                @SerializedName("last_name")
                val employeeAge: String,

                @SerializedName("avatar")
                val profileImage: String)
