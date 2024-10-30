package com.example.ventas.Screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ventas.Model.Producto
import com.example.ventas.Repository.ProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Productos(navController: NavController, productoRepository: ProductoRepository){
    val scope = rememberCoroutineScope()
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Gray.copy(alpha = 0.8f),
                contentColor = Color.White
            ) {
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
    ){
        AsyncImage(
            model = "https://images.unsplash.com/photo-1491895200222-0fc4a4c35e18?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8fA%3D%3D",
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        var productos by remember { mutableStateOf(listOf<Producto>()) }

        LaunchedEffect(Unit) {
            productos = productoRepository.getAllProductos()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = { navController.navigate("nuevoProducto/${0}") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
                    .size(120.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://img.icons8.com/?size=100&id=EDNaVIg1tDUS&format=png&color=000000",
                        contentDescription = "nuevoCliente",
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        text = "Nuevo Producto",
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFF0F0F0))
                    .padding(8.dp)
            ) {
                if (productos.isEmpty()){
                    item{
                        Text(
                            text = "No hay productos registrados",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    productos.forEach{ producto ->
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFFAFAFA), RoundedCornerShape(12.dp))
                                    .padding(16.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            Color(0xFFFAFAFA),
                                            RoundedCornerShape(12.dp)
                                        )
                                        .padding(16.dp)
                                        .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(end = 8.dp)
                                                .border(
                                                    1.dp,
                                                    Color.Gray,
                                                    RoundedCornerShape(8.dp)
                                                )
                                                .padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "ID",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(bottom = 4.dp)
                                            )
                                            Divider(color = Color.Gray, thickness = 1.dp)
                                            Text(
                                                text = "${producto.id}",
                                                fontSize = 14.sp,
                                                modifier = Modifier.padding(top = 4.dp)
                                            )
                                        }

                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(end = 8.dp)
                                                .border(
                                                    1.dp,
                                                    Color.Gray,
                                                    RoundedCornerShape(8.dp)
                                                )
                                                .padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Nombre",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(bottom = 4.dp)
                                            )
                                            Divider(color = Color.Gray, thickness = 1.dp)
                                            Text(
                                                text = "${producto.nombre}",
                                                fontSize = 14.sp,
                                                modifier = Modifier.padding(top = 4.dp)
                                            )
                                        }

                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(end = 8.dp)
                                                .border(
                                                    1.dp,
                                                    Color.Gray,
                                                    RoundedCornerShape(8.dp)
                                                )
                                                .padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Precio",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(bottom = 4.dp)
                                            )
                                            Divider(color = Color.Gray, thickness = 1.dp)
                                            Text(
                                                text = "${producto.precio}",
                                                fontSize = 14.sp,
                                                modifier = Modifier.padding(top = 4.dp)
                                            )
                                        }

                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(end = 8.dp)
                                                .border(
                                                    1.dp,
                                                    Color.Gray,
                                                    RoundedCornerShape(8.dp)
                                                )
                                                .padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Stock",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(bottom = 4.dp)
                                            )
                                            Divider(color = Color.Gray, thickness = 1.dp)
                                            Text(
                                                text = "${producto.stock}",
                                                fontSize = 14.sp,
                                                modifier = Modifier.padding(top = 4.dp)
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(end = 8.dp)
                                                .border(
                                                    1.dp,
                                                    Color.Gray,
                                                    RoundedCornerShape(8.dp)
                                                )
                                                .padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            IconButton(
                                                onClick = { navController.navigate("nuevoProducto/${producto.id}") },
                                                modifier = Modifier
                                                    .padding(bottom = 16.dp)
                                                    .size(30.dp)
                                            ) {
                                                AsyncImage(
                                                    model = "https://img.icons8.com/?size=100&id=4gHLhom6pq6u&format=png&color=000000",
                                                    contentDescription = "editarProducto",
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                            Divider(color = Color.Gray, thickness = 1.dp)
                                            IconButton(
                                                onClick = {
                                                    showDeleteConfirmation = true
                                                },
                                                modifier = Modifier
                                                    .padding(bottom = 16.dp)
                                                    .size(30.dp)
                                            ) {
                                                AsyncImage(
                                                    model = "https://img.icons8.com/?size=100&id=99961&format=png&color=000000",
                                                    contentDescription = "borrarProducto",
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }

                                            if (showDeleteConfirmation){
                                                AlertDialog(
                                                    onDismissRequest = {showDeleteConfirmation =false},
                                                    title = { Text(text="Confirmar Eliminacion")},
                                                    text = { Text(text = "¿Estas seguro de que deseas eliminar este producto?")},
                                                    confirmButton = {
                                                        Button(
                                                            onClick = {
                                                                showDeleteConfirmation = false
                                                                scope.launch {
                                                                    withContext(Dispatchers.IO) {
                                                                        productoRepository.delete(producto.id)
                                                                    }
                                                                    productos = withContext(Dispatchers.IO) {
                                                                        productoRepository.getAllProductos()
                                                                    }
                                                                }
                                                                Toast.makeText(context, "Registro Eliminado", Toast.LENGTH_SHORT).show()
                                                            }
                                                        ) {
                                                            Text("Aceptar")
                                                        }
                                                    },
                                                    dismissButton = {
                                                        Button(onClick = {showDeleteConfirmation=false}) {
                                                            Text("Cancelar")
                                                        }
                                                    }
                                                )
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}