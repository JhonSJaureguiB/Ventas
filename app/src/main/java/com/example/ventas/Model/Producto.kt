package com.example.ventas.Model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @NonNull
    val nombre: String,
    @NonNull
    val precio: Double,
    @NonNull
    val stock: Int
)
