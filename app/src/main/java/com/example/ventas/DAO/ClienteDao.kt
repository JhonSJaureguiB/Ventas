package com.example.ventas.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ventas.Model.Cliente


@Dao
interface ClienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: Cliente)

    @Query("SELECT * FROM clientes")
    suspend fun getAllClientes(): List<Cliente>

    @Query("DELETE FROM clientes WHERE id = :clienteId")
    suspend fun delete(clienteId: Int)

    @Update
    suspend fun update(cliente: Cliente)

    @Query("SELECT * FROM clientes WHERE id= :clienteId LIMIT 1")
    suspend fun getClienteById(clienteId: Int): Cliente?
}