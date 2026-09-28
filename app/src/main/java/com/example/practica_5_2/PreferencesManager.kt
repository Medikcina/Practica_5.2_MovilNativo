package com.example.practica_5_2.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    fun saveData(matricula: String, nombre: String, carrera: String, turno: String, estatus: Boolean) {
        prefs.edit().apply {
            putString("matricula", matricula)
            putString("nombre", nombre)
            putString("carrera", carrera)
            putString("turno", turno)
            putBoolean("estatus", estatus)
            apply()
        }
    }

    fun getMatricula(): String = prefs.getString("matricula", "") ?: ""
    fun getNombre(): String = prefs.getString("nombre", "") ?: ""
    fun getCarrera(): String = prefs.getString("carrera", "") ?: ""
    fun getTurno(): String = prefs.getString("turno", "Matutino") ?: "Matutino"
    fun getEstatus(): Boolean = prefs.getBoolean("estatus", true)
}