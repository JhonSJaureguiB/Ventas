package com.example.ventas.Repository

import com.example.ventas.DAO.ClienteDao
import com.example.ventas.Model.Cliente

class ClienteRepository(private val clienteDao: ClienteDao) {
    suspend fun insertar(cliente: Cliente){
        clienteDao.insert(cliente)
    }

    suspend fun getAllClientes(): List<Cliente>{
        return clienteDao.getAllClientes()
    }

    suspend fun delete(id: Int){
        clienteDao.delete(id)
    }

    suspend fun update(cliente: Cliente){
        clienteDao.update(cliente)
    }

    suspend fun getClienteById(clienteId: Int): Cliente?{
        return clienteDao.getClienteById(clienteId)
    }
}