package com.example.ventas.Screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.ventas.Model.Cliente
import com.example.ventas.Model.Producto
import com.example.ventas.Repository.ProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun nuevoProducto(navController: NavHostController, productoRepository: ProductoRepository, productoId: Int
){
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("0.0") }
    var stock by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(productoId) {
        withContext(Dispatchers.IO){
            val producto = productoRepository.getProductoById(productoId)
            producto?.let{
                nombre = it.nombre
                precio = it.precio.toString()
                stock = it.stock.toString()
            }
        }
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Gray.copy(alpha = 0.8f),
                contentColor = Color.White
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },

                    ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Regresar",
                        tint = Color.White
                    )
                }
                Box(modifier = Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {
                        navController.navigate("items")
                    }) {
                        Icon(Icons.Default.Home, contentDescription = "itmes")
                    }
                    Text(text = "Inicio", color = Color.White)
                }
                Box(modifier = Modifier.weight(1f))
            }
        }
    ) {
        AsyncImage(
            model = "https://images.unsplash.com/photo-1491895200222-0fc4a4c35e18?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8fA%3D%3D",
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            Modifier
                .padding(24.dp)
                .fillMaxSize(), Arrangement.Top, Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = if (productoId == 0) "Registro de Producto" else "Editar Producto",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE8EAF6),
                    focusedIndicatorColor = Color(0xFF3E4EB8),
                    unfocusedIndicatorColor = Color(0xFF3E4EB8)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE8EAF6),
                    focusedIndicatorColor = Color(0xFF3E4EB8),
                    unfocusedIndicatorColor = Color(0xFF3E4EB8)
                )
            )

            TextField(
                value = stock,
                onValueChange = { stock = it },
                label = { Text("Stock") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE8EAF6),
                    focusedIndicatorColor = Color(0xFF3E4EB8),
                    unfocusedIndicatorColor = Color(0xFF3E4EB8)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    scope.launch {
                        if (productoId == 0) {
                            val producto = Producto(nombre = nombre, precio = precio.toDoubleOrNull() ?: 0.0, stock = stock.toIntOrNull() ?: 0)
                            withContext(Dispatchers.IO) { productoRepository.insertar(producto) }
                            Toast.makeText(context, "Producto Registrado", Toast.LENGTH_SHORT).show()
                        } else {
                            val producto = Producto(id = productoId, nombre = nombre, precio = precio.toDoubleOrNull() ?:0.0, stock = stock.toIntOrNull() ?: 0)
                            withContext(Dispatchers.IO) { productoRepository.update(producto) }
                            Toast.makeText(context, "Producto Actualizado", Toast.LENGTH_SHORT).show()
                        }
                        navController.navigate("productos")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3E4EB8),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Guardar", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
        }
    }
}