package com.example.ventas.Model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "ventas", foreignKeys = [
    ForeignKey(
        entity = Cliente::class,
        parentColumns = ["id"],
        childColumns = ["cliente_id"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Producto::class,
        parentColumns = ["id"],
        childColumns = ["producto_id"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Venta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    val producto_id: Int,
    @NonNull
    val cliente_id: Int,
    @NonNull
    val fecha: Date
)
