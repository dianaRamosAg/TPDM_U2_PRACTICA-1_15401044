package mx.edu.ittepic.tpdm_u2_pratica1_15401044

import android.content.Intent
import android.database.sqlite.SQLiteException
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main4.*

class Main2Activity : AppCompatActivity() {

    var btnReg:Button?=null
    var btnCL: Button?=null
    var Ldesc: EditText?=null
    var Lfech: EditText?=null
    var basedatos = BaseDatos(this,"PRACTICA1",null,1)   //Conexion para SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnReg=findViewById(R.id.btnRegresar)
        btnCL=findViewById(R.id.btnCrearLista)
        Ldesc=findViewById(R.id.txtDescLista)
        Lfech=findViewById(R.id.txtFechLista)



        btnCL?.setOnClickListener {
            insertarLista()
        }
        btnReg?.setOnClickListener {
            var principal = Intent(this,MainActivity::class.java)
            startActivity(principal)
        }
    }//OnCreate

    fun insertarLista() {
        try {
            var transaccion = basedatos.writableDatabase
            var SQL ="INSERT INTO LISTA VALUES(null,'DESCRIPCION','FECHACREACION')"

            SQL = SQL.replace("DESCRIPCION",Ldesc?.text.toString())
            SQL = SQL.replace("FECHACREACION",Lfech?.text.toString())

            transaccion.execSQL(SQL)
            transaccion.close()
            mensaje("EXITO", "SE CREO CORRECTAMENTE LA LISTA ")
            limpiarCampos()
        }catch (err: SQLiteException){
            mensaje("Error", "NO SE CREO LA LISTA ")
        }
    }// ilseanumis




    //--------------------------------------------------------------------------------------------------------
    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("OK") { dialog, which -> }.show()
    }//Funcion mensaje

    fun limpiarCampos() {
        Ldesc?.setText("")
        Lfech?.setText("")
    }//FuncionLimpiarCamposs


}//Class