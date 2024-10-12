package com.glenda.aulasqlite2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.glenda.aulasqlite2.bancodedados.DataBaseHelper
import java.lang.Exception
//Aula do dia 01/10/24
class MainActivity : AppCompatActivity() {
    private val bancoDados by lazy {
        DataBaseHelper(this)
    }
    private lateinit var editNomeProduto:EditText
    private lateinit var btnSalvar:Button
    private lateinit var btnListar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editNomeProduto = findViewById(R.id.editNomeProduto )
        btnSalvar = findViewById(R.id.btnSalvar)
        btnListar = findViewById(R.id.btnListar)

        btnSalvar.setOnClickListener{
            salvar()

        }
        btnListar.setOnClickListener{
            listar()
        }

    }
    private fun salvar(){
        val nomeProduto = editNomeProduto.text.toString()

        try {
            val sql = "INSERT INTO produtos VALUES(null, '$nomeProduto', '512gb')"
            bancoDados.writableDatabase.execSQL(sql)
            Log.i("db_info", "Produto salvo")
        }catch (e:Exception){
            e.printStackTrace()
            Log.i("db_info",  "Erro ao salvar produto")
        }
    }

    private fun listar(){
        val sql = "SELECT * FROM produtos"
        val cursor= bancoDados.readableDatabase.rawQuery(sql, null )

        while(cursor.moveToNext()){
            val idProduto = cursor.getInt(0)
            val titulo = cursor.getString(1)
            val descricao = cursor.getString(2)
            Log.i("db_info", "Produtos: $idProduto - $titulo - $descricao")
        }
    }
}