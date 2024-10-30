package com.example.ventas.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ventas.Model.Venta

@Dao
interface VentaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(venta: Venta)

    @Query("SELECT * FROM ventas")
    suspend fun getAllVenta(): List<Venta>

    @Query("DELETE FROM ventas WHERE id = :ventaId")
    suspend fun delete(ventaId: Int)

    @Update
    suspend fun update(venta: Venta)

    @Query("SELECT * FROM ventas WHERE id= :ventaId LIMIT 1")
    suspend fun getVentasById(ventaId: Int): Venta?

    @Query("SELECT nombre FROM Clientes WHERE id = :clienteId LIMIT 1")
    suspend fun getNombreCliente(clienteId: Int): String?

    @Query("SELECT nombre FROM Productos WHERE id = :productoId LIMIT 1")
    suspend fun getNombreProducto(productoId: Int): String?
}