package com.example.app_s9

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextEdad: EditText

    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonReset: Button

    private lateinit var textViewResult: TextView
    private lateinit var switchDarkMode: SwitchCompat
    private lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        // Inicializar vistas
        initViews()

        // Contador de visitas
        val visitCount = sharedPreferencesHelper.getVisitCount() + 1
        sharedPreferencesHelper.saveVisitCount(visitCount)
        updateVisitCounter(visitCount)

        // Aplicar modo oscuro si ya estaba activado
        val isDark = sharedPreferencesHelper.getBoolean("modo_oscuro", false)
        switchDarkMode.isChecked = isDark
        applyTheme(isDark)

        // Verificar si es la primera vez
        checkFirstTime()

        // Listeners
        setupListeners()
    }

    private fun initViews() {
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextEdad = findViewById(R.id.editTextEdad)

        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)
        buttonClear = findViewById(R.id.buttonClear)
        buttonReset = findViewById(R.id.buttonReset)

        textViewResult = findViewById(R.id.textViewResult)
        switchDarkMode = findViewById(R.id.switchDarkMode)

        rootLayout = findViewById(R.id.main)
    }

    private fun setupListeners() {
        buttonSave.setOnClickListener {
            saveData()
        }

        buttonLoad.setOnClickListener {
            loadData()
        }

        buttonClear.setOnClickListener {
            clearAllData()
        }

        buttonReset.setOnClickListener {
            sharedPreferencesHelper.saveVisitCount(0)
            updateVisitCounter(0)
            Toast.makeText(this, "Contador de visitas reseteado", Toast.LENGTH_SHORT).show()
        }

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferencesHelper.saveBoolean("modo_oscuro", isChecked)
            applyTheme(isChecked)
        }
    }

    private fun saveData() {
        val username = editTextUsername.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val edadStr = editTextEdad.text.toString().trim()

        if (username.isEmpty() || email.isEmpty() || edadStr.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        sharedPreferencesHelper.saveString("username", username)
        sharedPreferencesHelper.saveString("email", email)
        sharedPreferencesHelper.saveInt("edad", edadStr.toInt())
        sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, false)
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_USER_ID, (1000..9999).random())

        Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
        editTextUsername.setText("")
        editTextEmail.setText("")
        editTextEdad.setText("")
    }

    private fun loadData() {
        val username = sharedPreferencesHelper.getString("username", "Sin nombre")
        val email = sharedPreferencesHelper.getString("email", "Sin email")
        val edad = sharedPreferencesHelper.getInt("edad", 0)
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        val userId = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_USER_ID, 0)

        val result = "Usuario: $username\nEmail: $email\nEdad: $edad\nID: $userId\nPrimera vez: ${if (isFirstTime) "Sí" else "No"}"
        textViewResult.text = result
    }

    private fun clearAllData() {
        sharedPreferencesHelper.clearAll()
        editTextUsername.setText("")
        editTextEmail.setText("")
        editTextEdad.setText("")
        textViewResult.text = ""
        Toast.makeText(this, "Preferencias eliminadas", Toast.LENGTH_SHORT).show()
    }

    private fun updateVisitCounter(count: Int) {
        textViewResult.text = "Visitas: $count"
    }

    private fun checkFirstTime() {
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        if (isFirstTime) {
            Toast.makeText(this, "¡Bienvenido por primera vez!", Toast.LENGTH_LONG).show()
        }
    }

    private fun applyTheme(isDark: Boolean) {
        if (isDark) {
            rootLayout.setBackgroundColor(Color.DKGRAY)
            textViewResult.setTextColor(Color.WHITE)
            editTextUsername.setTextColor(Color.WHITE)
            editTextEmail.setTextColor(Color.WHITE)
            editTextEdad.setTextColor(Color.WHITE)
        } else {
            rootLayout.setBackgroundColor(Color.WHITE)
            textViewResult.setTextColor(Color.BLACK)
            editTextUsername.setTextColor(Color.BLACK)
            editTextEmail.setTextColor(Color.BLACK)
            editTextEdad.setTextColor(Color.BLACK)
        }
    }
}
