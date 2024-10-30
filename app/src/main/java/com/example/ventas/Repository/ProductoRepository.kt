package com.example.ventas.Repository

import com.example.ventas.DAO.ProductoDao
import com.example.ventas.Model.Producto


class ProductoRepository (private val productoDao: ProductoDao) {
    suspend fun insertar(cliente: Producto){
        productoDao.insert(cliente)
    }

    suspend fun getAllProductos(): List<Producto>{
        return productoDao.getAllProductos()
    }

    suspend fun delete(id: Int){
        productoDao.delete(id)
    }

    suspend fun update(producto: Producto){
        productoDao.update(producto)
    }

    suspend fun getProductoById(productoId: Int): Producto?{
        return productoDao.getProductoById(productoId)
    }

    suspend fun updateStock(productoId: Int, nuevoStock: Int){
        return productoDao.updateStock(productoId, nuevoStock)
    }
}