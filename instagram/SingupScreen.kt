package com.example.instagram

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SingupScreen : AppCompatActivity() {

    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var edtname: EditText
    private lateinit var edtmobile: EditText
    private lateinit var edtabout: EditText
    private lateinit var bsignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_singup_screen)

        edtemail = findViewById(R.id.editText_emailAddress)
        edtpassword = findViewById(R.id.editText_password)
        edtname = findViewById(R.id.editText_name)
        edtmobile = findViewById(R.id.editText_mobile)
        edtabout = findViewById(R.id.about)
        bsignup = findViewById(R.id.bttnsignup)

        // Adjust padding to avoid content overlapping system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bsignup.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val email = edtemail.text.toString()
        val password = edtpassword.text.toString()
        val name = edtname.text.toString()
        val mobile = edtmobile.text.toString()
        val about = edtabout.text.toString()

        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || mobile.isEmpty() || about.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        } else {
            val progressDialog = ProgressDialog(this).apply {
                setMessage("Registering User...")
                show()
            }

            val userId = System.currentTimeMillis().toString()
            val user = User(userId, name, email, mobile, about)

            // Save user data in SharedPreferences
            val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(userId, user.toJson())  // Convert user to JSON string
            editor.apply()

            progressDialog.dismiss()
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()

            // Clear input fields
            edtemail.text.clear()
            edtpassword.text.clear()
            edtname.text.clear()
            edtmobile.text.clear()
            edtabout.text.clear()

            // Start UserListActivity
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }
}
