package com.example.ventas.Repository


import com.example.ventas.DAO.VentaDao
import com.example.ventas.Model.Venta


class VentaRepository(private val ventaDao: VentaDao) {
    suspend fun insertar(cliente: Venta){
        ventaDao.insert(cliente)
    }

    suspend fun getAllVentas(): List<Venta>{
        return ventaDao.getAllVenta()
    }

    suspend fun delete(id: Int){
        ventaDao.delete(id)
    }

    suspend fun update(producto: Venta){
        ventaDao.update(producto)
    }

    suspend fun getVentasById(ventaId: Int): Venta?{
        return ventaDao.getVentasById(ventaId)
    }

    suspend fun getNombreCliente(clienteId: Int): String?{
        return ventaDao.getNombreCliente(clienteId)
    }

    suspend fun getNombreProducto(productoId: Int): String?{
        return ventaDao.getNombreProducto(productoId)
    }
}