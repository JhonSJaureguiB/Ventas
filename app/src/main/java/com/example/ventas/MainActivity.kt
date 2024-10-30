package com.example.ventas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ventas.DAO.ClienteDao
import com.example.ventas.DAO.ProductoDao
import com.example.ventas.DAO.VentaDao
import com.example.ventas.DataBase.VentasDatabase
import com.example.ventas.Repository.ClienteRepository
import com.example.ventas.Repository.ProductoRepository
import com.example.ventas.Repository.VentaRepository
import com.example.ventas.Screen.Controlar

class MainActivity : ComponentActivity() {
    private lateinit var clienteDao: ClienteDao
    private lateinit var productoDao: ProductoDao
    private lateinit var ventaDao: VentaDao

    private lateinit var clienteRepository: ClienteRepository
    private lateinit var productoRepository: ProductoRepository
    private lateinit var ventaRepository: VentaRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = VentasDatabase.getDataBase(applicationContext)
        clienteDao = db.clienteDao()
        clienteRepository = ClienteRepository(clienteDao)

        productoDao = db.productoDao()
        productoRepository = ProductoRepository(productoDao)

        ventaDao = db.ventaDao()
        ventaRepository= VentaRepository(ventaDao)

        enableEdgeToEdge()
        setContent {
            Controlar(clienteRepository,productoRepository,ventaRepository)
        }
    }
}
