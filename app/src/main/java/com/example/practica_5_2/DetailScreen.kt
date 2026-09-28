package com.example.practica_5_2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    navController: NavController,
    matricula: String,
    nombre: String,
    carrera: String,
    turno: String,
    estatus: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Confirmación de Datos", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(32.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Matrícula: $matricula", style = MaterialTheme.typography.titleMedium)
                Text("Nombre: $nombre", style = MaterialTheme.typography.titleMedium)
                Text("Carrera: $carrera", style = MaterialTheme.typography.titleMedium)
                Text("Turno: $turno", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "Estatus: ${if (estatus) "Activo" else "Inactivo"}",
                    style = MaterialTheme.typography.titleMedium,
                    color = if (estatus) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al Registro")
        }
    }
}