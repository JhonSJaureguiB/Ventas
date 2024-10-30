package com.example.ventas.Screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import android.app.DatePickerDialog
import androidx.compose.material.icons.Icons
import android.widget.DatePicker
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ventas.Model.Cliente
import com.example.ventas.Model.Producto
import com.example.ventas.Model.Venta
import com.example.ventas.Repository.ClienteRepository
import com.example.ventas.Repository.ProductoRepository
import com.example.ventas.Repository.VentaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun nuevaVenta(
    navController: NavController,
    ventaRepository: VentaRepository,
    clienteRepository: ClienteRepository,
    productoRepository: ProductoRepository,
    ventaId: Int
) {
    var menu by remember { mutableStateOf(false)}
    var menu1 by remember { mutableStateOf(false)}
    var fechaElejida by remember { mutableStateOf("") }
    var producto_id by remember { mutableStateOf(0) }
    var producto_stock by remember { mutableStateOf(0) }
    var cliente_id by remember { mutableStateOf(0) }
    var infCliente by remember { mutableStateOf("Seleccione un cliente")}
    var infProducto by remember { mutableStateOf("Seleccione una producto")}
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var clientes by remember { mutableStateOf(listOf<Cliente>()) }
    var productos by remember { mutableStateOf(listOf<Producto>()) }
    val calendario = Calendar.getInstance()
    val formatoFecha ="dd-MM-yyyy"
    val formatosimple = SimpleDateFormat(formatoFecha, Locale.ENGLISH)
    val fecha = DatePickerDialog.OnDateSetListener{datepicker, year, month, day ->
        calendario.set(Calendar.YEAR, year)
        calendario.set(Calendar.MONTH, month)
        calendario.set(Calendar.DAY_OF_MONTH, day)


        fechaElejida = formatosimple.format(calendario.time)
    }


    LaunchedEffect(ventaId) {
        withContext(Dispatchers.IO) {
            val venta = ventaRepository.getVentasById(ventaId)
            venta?.let {
                producto_id = it.producto_id
                cliente_id = it.cliente_id
                fechaElejida = formatosimple.format(it.fecha)

                val productoSeleccionado = productos.find { producto -> producto.id == producto_id }
                val clienteSeleccionado = clientes.find { cliente -> cliente.id == cliente_id }

                infProducto = productoSeleccionado?.let { "${it.id}. ${it.nombre}" } ?: "Seleccione un producto"
                infCliente = clienteSeleccionado?.let { "${it.id}. ${it.nombre}" } ?: "Seleccione un cliente"
            }
        }
    }

    LaunchedEffect(Unit) {
        clientes = clienteRepository.getAllClientes()
        productos = productoRepository.getAllProductos()

        if (ventaId != 0) {
            val venta = ventaRepository.getVentasById(ventaId)
            venta?.let {
                producto_id = it.producto_id
                cliente_id = it.cliente_id
                fechaElejida = formatosimple.format(it.fecha)

                val productoSeleccionado = productos.find { producto -> producto.id == producto_id }
                val clienteSeleccionado = clientes.find { cliente -> cliente.id == cliente_id }

                infProducto = productoSeleccionado?.let { "${it.id}. ${it.nombre}" } ?: "Seleccione un producto"
                infCliente = clienteSeleccionado?.let { "${it.id}. ${it.nombre}" } ?: "Seleccione un cliente"
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
                        Icon(Icons.Default.Home, contentDescription = "items")
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
                text = if (ventaId == 0) "Registro de Venta" else "Editar Venta",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Box{
                Row (modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE8EAF6))
                    .padding(12.dp)
                    .clickable { menu = true }
                ){
                    Text(
                        text = infProducto,
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Desplegar menú")
                }
                DropdownMenu(
                    expanded = menu,
                    onDismissRequest = { menu = false },
                    modifier = Modifier.wrapContentSize(Alignment.TopStart)
                ) {
                    productos.filter { it.stock > 0 }.forEach { producto ->
                        DropdownMenuItem(
                            text = { Text("${producto.id}. ${producto.nombre}") },
                            onClick = {
                                infProducto = "${producto.id}. ${producto.nombre}"
                                producto_id = producto.id
                                producto_stock = producto.stock - 1
                                menu = false
                            }
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            Box {
                Row(modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE8EAF6))
                    .padding(12.dp)
                    .clickable { menu1 = true }
                ) {
                    Text(
                        text = infCliente,
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Desplegar menú")
                }
                DropdownMenu(
                    expanded = menu1,
                    onDismissRequest = { menu1 = false },
                    modifier = Modifier.wrapContentSize(Alignment.TopStart)
                ) {
                    clientes.forEach { cliente ->
                        DropdownMenuItem(
                            text = { Text("${cliente.id}. ${cliente.nombre}") },
                            onClick = {
                                infCliente = "${cliente.id}. ${cliente.nombre}"
                                cliente_id = cliente.id
                                menu1 = false
                            }

                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = fechaElejida ,
                    onValueChange = { fechaElejida = it },
                    label = { Text("Seleccione la fecha") },
                    modifier = Modifier
                        .weight(1f) ,
                    readOnly = true
                )

                IconButton(onClick = {
                    DatePickerDialog(
                        context,
                        fecha,
                        calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }) {
                    AsyncImage(
                        model = "https://img.icons8.com/?size=100&id=12776&format=png&color=000000",
                        contentDescription = "Producto",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        if (ventaId == 0) {
                            val venta = Venta(
                                producto_id = producto_id,
                                cliente_id = cliente_id,
                                fecha = formatosimple.parse(fechaElejida)
                            )
                            withContext(Dispatchers.IO) { ventaRepository.insertar(venta) }
                            withContext(Dispatchers.IO){ productoRepository.updateStock(producto_id,producto_stock)}
                            Toast.makeText(context, "Venta Registrada", Toast.LENGTH_SHORT).show()
                        } else {
                            val venta = Venta(
                                id = ventaId,
                                producto_id = producto_id,
                                cliente_id = cliente_id,
                                fecha = formatosimple.parse(fechaElejida)
                            )
                            withContext(Dispatchers.IO) { ventaRepository.update(venta) }
                            Toast.makeText(context, "Venta Actualizada", Toast.LENGTH_SHORT).show()
                        }
                        navController.navigate("ventas")
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

