package com.example.ventas.Screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ventas.Repository.ClienteRepository
import com.example.ventas.Repository.ProductoRepository
import com.example.ventas.Repository.VentaRepository

@Composable
fun Controlar(clienteRepository: ClienteRepository, productoRepository: ProductoRepository, ventaRepository: VentaRepository
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "items"){
        composable("items"){
            Inicio(navController)
        }
        composable(route= "clientes"){
            Clientes(navController, clienteRepository)
        }
        composable(route= "nuevoCliente/{clienteId}"){backStackEntry ->
            val clienteId = backStackEntry.arguments?.getString("clienteId")?.toInt()?:0
            nuevoCliente(navController, clienteRepository, clienteId)
        }

        composable(route= "productos"){
            Productos(navController, productoRepository)
        }

        composable(route= "nuevoProducto/{productoId}"){backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")?.toInt()?:0
            nuevoProducto(navController, productoRepository, productoId)
        }

        composable(route= "ventas"){
            Ventas(navController, ventaRepository)
        }

        composable(route= "nuevaVenta/{ventaId}"){backStackEntry ->
            val ventaId = backStackEntry.arguments?.getString("ventaId")?.toInt()?:0
            nuevaVenta(navController, ventaRepository, clienteRepository, productoRepository, ventaId)
        }
    }
}