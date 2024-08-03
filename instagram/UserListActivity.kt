package com.example.instagram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_users)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Load user data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val userList = sharedPreferences.all.values.mapNotNull { json ->
            Gson().fromJson(json.toString(), User::class.java)
        }

        val adapter = UserAdapter(userList)
        recyclerView.adapter = adapter
    }
}
