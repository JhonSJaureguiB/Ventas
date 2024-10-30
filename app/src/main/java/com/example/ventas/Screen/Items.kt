package com.example.ventas.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Inicio(navController: NavController) {
    AsyncImage(
        model = "https://images.unsplash.com/photo-1491895200222-0fc4a4c35e18?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8fA%3D%3D",
        contentDescription = "Imagen de fondo",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Primer IconButton
            IconButton(
                onClick = {navController.navigate("clientes")},
                modifier = Modifier.size(160.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://cdn-icons-png.flaticon.com/256/10729/10729125.png",
                        contentDescription = "Cliente",
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = "Clientes",
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            IconButton(
                onClick = {navController.navigate("productos")},
                modifier = Modifier.size(160.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://cdn-icons-png.flaticon.com/512/1044/1044967.png",
                        contentDescription = "Producto",
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = "Productos",
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            IconButton(
                onClick = {navController.navigate("ventas")},
                modifier = Modifier.size(160.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://cdn-icons-png.flaticon.com/512/6712/6712868.png",
                        contentDescription = "Ventas",
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = "Ventas",
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
