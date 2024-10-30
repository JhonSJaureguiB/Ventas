package com.example.ventas.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ventas.Model.Producto

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: Producto)

    @Query("SELECT * FROM productos")
    suspend fun getAllProductos(): List<Producto>

    @Query("DELETE FROM productos WHERE id = :productoId")
    suspend fun delete(productoId: Int)

    @Update
    suspend fun update(producto: Producto)

    @Query("SELECT * FROM productos WHERE id= :productoId LIMIT 1")
    suspend fun getProductoById(productoId: Int): Producto?

    @Query("UPDATE productos SET stock = :nuevoStock WHERE id = :productoId")
    suspend fun updateStock(productoId: Int, nuevoStock: Int)
}