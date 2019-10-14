package mx.edu.ittepic.tpdm_u2_pratica1_15401044

import android.content.Intent
import android.database.sqlite.SQLiteException
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {
    var btnGLT: Button? = null
    var verListas: Button? = null
    var tareas: Button? = null
    var ListasM : ListView?= null
    var ListasGen: ArrayList<String> = ArrayList()
    var basedatos = BaseDatos(this,"PRACTICA1",null,1)   //Conexion para SQLite
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGLT = findViewById(R.id.GenerarListaTareas)
        verListas = findViewById(R.id.VListas)
        tareas = findViewById(R.id.tareas)
        ListasM=findViewById(R.id.Listas)

        MostrarListas()

        btnGLT?.setOnClickListener {
            var genlist = Intent(this, Main2Activity::class.java)
            startActivity(genlist)
        }
        verListas?.setOnClickListener {
            var list = Intent(this, Main4Activity::class.java)
            startActivity(list)
        }
        tareas?.setOnClickListener {
            var tarea = Intent(this, Main3Activity::class.java)
            startActivity(tarea)
        }
    }
    fun MostrarListas(){

        try {
            var transaccion = basedatos.writableDatabase
            var SQL="SELECT * FROM LISTA"

            var  cursor = transaccion.rawQuery(SQL,null)
            if (cursor.moveToFirst()==true){
                do{
                    ListasGen?.add(cursor.getString(0)+" | "+cursor.getString(1)+" | "+cursor.getString(2))

                }while(cursor.moveToNext())
            }
            cursor.close()
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ListasGen)
            ListasM?.setAdapter(adapter);

        }catch (err: SQLiteException){
        }
    }

}
