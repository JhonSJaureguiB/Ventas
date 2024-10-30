package com.example.ventas.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ventas.DAO.ClienteDao
import com.example.ventas.DAO.ProductoDao
import com.example.ventas.DAO.VentaDao
import com.example.ventas.Model.Cliente
import com.example.ventas.Model.Producto
import com.example.ventas.Model.Venta
import com.example.ventas.converter.Converters

@Database(entities = [Producto::class, Cliente::class, Venta::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class VentasDatabase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDao
    abstract fun productoDao(): ProductoDao
    abstract fun ventaDao(): VentaDao

    companion object{
        @Volatile
        private var INSTANCE: VentasDatabase? = null

        fun getDataBase(context: Context): VentasDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VentasDatabase::class.java,
                    "ventas_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}