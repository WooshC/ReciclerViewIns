package com.example.instagram

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoginScreen : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var btnIniciar: TextView
    private lateinit var btnRegistrar: TextView
    private lateinit var recyclerView: RecyclerView

    private val validEmail = "Moises"
    private val validPassword = "123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        // Inicializar vistas
        emailEditText = findViewById(R.id.editText_emailAddress)
        passwordEditText = findViewById(R.id.editText_password)
        loginButton = findViewById(R.id.blogin)
        btnIniciar = findViewById(R.id.btn_Iniciar)
        btnRegistrar = findViewById(R.id.btn_Registrar)
        recyclerView = findViewById(R.id.recyclerView)

        // Configurar RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NewsAdapter(getNewsData())

        // Configurar listeners
        loginButton.setOnClickListener {
            attemptLogin()
        }

        btnIniciar.setOnClickListener {
            attemptLogin()
        }

        btnRegistrar.setOnClickListener {
            navigateToSignup()
        }
    }

    private fun attemptLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isBlank()) {
            showToast("Please enter your email")
        } else if (password.isBlank()) {
            showToast("Please enter your password")
        } else if (email == validEmail && password == validPassword) {
            showToast("Login successful")
            // Aquí puedes agregar la lógica para navegar a otra pantalla después del login
            finish()
        } else {
            showToast("Invalid email or password")
        }
    }

    private fun navigateToSignup() {
        startActivity(Intent(this, SingupScreen::class.java))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getNewsData(): List<NewsItem> {
        // Proporciona datos de ejemplo para el RecyclerView
        return listOf(
            NewsItem("Title 1", "Description 1", "Date 1"),
            NewsItem("Title 2", "Description 2", "Date 2"),
            NewsItem("Title 3", "Description 3", "Date 3")
        )
    }
}

