package mx.edu.ittepic.tpdm_u2_pratica1_15401044


import android.content.Intent
import android.database.sqlite.SQLiteException
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class Main3Activity : AppCompatActivity() {
    var btnReg:Button?=null
    var btnCrearTarea:Button?=null
    var Tdesc: EditText?=null
    var Tfech: EditText?=null
    var IDL: EditText?=null

    @RequiresApi(Build.VERSION_CODES.P)
    var basedatos = BaseDatos(this,"PRACTICA2",null,1)   //Conexion para SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        btnReg=findViewById(R.id.btnRegresar)
        btnCrearTarea=findViewById(R.id.btnCrearTarea)
        Tdesc=findViewById(R.id.txtDescTarea)
        Tfech=findViewById(R.id.txtFechTarea)
        IDL=findViewById(R.id.txtIDLista)

        btnReg?.setOnClickListener {
            var principal = Intent(this,MainActivity::class.java)
            startActivity(principal)
        }
        btnCrearTarea?.setOnClickListener {
            crearTarea()
        }
    }//Oncretae

    fun crearTarea()
    {
        try {
            var transaccion = basedatos.writableDatabase
            var SQL = "INSERT INTO TAREA VALUES(null,'DESCRIPCION','REALIZADO',ID_L)"
            SQL = SQL.replace("DESCRIPCION",Tdesc?.text.toString())
            SQL = SQL.replace("REALIZADO",Tfech?.text.toString())
            SQL = SQL.replace("ID_L",IDL?.text.toString())
            transaccion.execSQL(SQL)
            transaccion.close()
            mensaje("EXITO", "SE CREO CORRECTAMENTE LA TAREA ")
            limpiarCampos()
        } catch (err: SQLiteException) {
            mensaje("ERROR", "NO SE PUDO CREAR LA TAREA")
        }
    }


    //--------------------------------------------------------------------------------------------------------
    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("OK") { dialog, which -> }.show()
    }//Funcion mensaje

    fun limpiarCampos() {
        Tdesc?.setText("")
        Tfech?.setText("")
        IDL?.setText("")
    }//FuncionLimpiarCamposs



}//Class
