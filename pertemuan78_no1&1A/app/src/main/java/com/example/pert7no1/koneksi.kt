package com.example.pert7no1

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object koneksi {
    private const val URL = "jdbc:postgresql://10.0.2.2:5432/coba_1"
    private const val USER = "postgres"
    private const val PASSWORD = "123"

    fun connection (): Connection?{
        return try{
            Class.forName("org.postgresql.Driver")
            DriverManager.getConnection(URL,USER, PASSWORD).also {
            }
        }catch (e: SQLException){
            e.printStackTrace()
            println("Hahahaha")
            null
        }
    }

}