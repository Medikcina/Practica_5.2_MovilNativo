package com.example.practica_5_2.screens

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica_5_2.data.PreferencesManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(navController: NavController) {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }

    // Estados cargados desde SharedPreferences
    var matricula by remember { mutableStateOf(prefs.getMatricula()) }
    var nombre by remember { mutableStateOf(prefs.getNombre()) }
    var carrera by remember { mutableStateOf(prefs.getCarrera()) }
    var turno by remember { mutableStateOf(prefs.getTurno()) }
    var estatus by remember { mutableStateOf(prefs.getEstatus()) }

    // Opciones para el DropdownMenu
    val opcionesCarrera = listOf("Ingeniería en Software", "Ingeniería Civil", "Ingeniería Geodésica", "Arquitectura")
    var expandedDropdown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // Evita que choque con la barra superior y los botones de navegación
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro de Estudiante", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = matricula,
            onValueChange = { matricula = it },
            label = { Text("Matrícula") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre Completo") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // DropdownMenu para Carrera
        ExposedDropdownMenuBox(
            expanded = expandedDropdown,
            onExpandedChange = { expandedDropdown = !expandedDropdown }
        ) {
            OutlinedTextField(
                value = carrera.ifEmpty { "Selecciona una carrera" },
                onValueChange = {},
                readOnly = true,
                label = { Text("Carrera") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDropdown) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandedDropdown,
                onDismissRequest = { expandedDropdown = false }
            ) {
                opcionesCarrera.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            carrera = opcion
                            expandedDropdown = false
                        }
                    )
                }
            }
        }

        // RadioButtons para Turno
        Text("Turno:", modifier = Modifier.align(Alignment.Start))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = turno == "Matutino", onClick = { turno = "Matutino" })
                Text("Matutino")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = turno == "Vespertino", onClick = { turno = "Vespertino" })
                Text("Vespertino")
            }
        }

        // Switch para Estatus
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Estatus: ${if (estatus) "Activo" else "Inactivo"}")
            Switch(checked = estatus, onCheckedChange = { estatus = it })
        }

        // Espacio corto fijo para que el botón quede arriba, justo debajo del Switch
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (matricula.isNotBlank() && nombre.isNotBlank() && carrera.isNotBlank()) {
                    // 1. Guardar localmente
                    prefs.saveData(matricula, nombre, carrera, turno, estatus)

                    // 2. Navegar y pasar parámetros
                    val route = "detail/${matricula}/${Uri.encode(nombre)}/${Uri.encode(carrera)}/${turno}/${estatus}"
                    navController.navigate(route)
                } else {
                    Toast.makeText(context, "Llena todos los campos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar y Ver Detalles")
        }
    }
}