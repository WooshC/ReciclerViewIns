package com.example.instagram
import com.google.gson.Gson
data class User(val id: String, val name: String, val email: String, val mobile: String, val about: String) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}

