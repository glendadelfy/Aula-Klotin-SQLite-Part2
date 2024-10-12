package com.glenda.aulasqlite2.bancodedados

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class DataBaseHelper(context:Context):SQLiteOpenHelper(
    //1.Context
    //2.Nome do banco de dados
    //3.CursorFactory
    //4.Versão do banco

    context,"loja",null,1

) {
    override fun onCreate(db: SQLiteDatabase?) {
        //É executado uma unica vez, quando o app é instalado
        val sql = "CREATE TABLE IF NOT EXISTS produtos(\n" +
                "  id_produto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  titulo VARCHAR(100),\n" +
                "  descricao TEXT\n" +
                "  )"
        try {
            db?.execSQL(sql)
            Log.i("db_info", "Tabela criada com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Error ao criar tabela")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //É executado quando há mudança de versão do banco
    }

}