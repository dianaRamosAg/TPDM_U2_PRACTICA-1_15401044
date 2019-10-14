package mx.edu.ittepic.tpdm_u2_pratica1_15401044


import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class Main4Activity : AppCompatActivity() {
    var btnReg: Button? = null
    var btnBuscar: Button? = null
    var matareas: Button? = null
    var basedatos = BaseDatos(this, "PRACTICA1", null, 1)   //Conexion para SQLite
    var idL: EditText? = null
    var descL: EditText? = null
    var fechaL: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        btnReg = findViewById(R.id.btnRegresar)
        btnBuscar = findViewById(R.id.btnBuscarLista)
        idL = findViewById(R.id.txtIDLista)
        descL = findViewById(R.id.txtDescripcionLista)
        fechaL = findViewById(R.id.txtFechaCreacionLista)

        btnReg?.setOnClickListener {
            var principal = Intent(this, MainActivity::class.java)
            startActivity(principal)
        }
        btnBuscar?.setOnClickListener {
            pedirID()
        }
        matareas?.setOnClickListener {
            var mastareas = Intent(this, Main5Activity::class.java)
            startActivity(mastareas)
        }
    }//OnCreate

    fun pedirID() {
        var campo = EditText(this)
        campo.inputType = InputType.TYPE_CLASS_NUMBER

        AlertDialog.Builder(this).setTitle("ATENCION")
            .setMessage("Escribe el ID a buscar:  ").setView(campo)
            .setPositiveButton("OK") { dialog, which ->
                buscarLista(campo.text.toString())
            }
            .setNeutralButton("CANCELAR") { dialog, which -> }
            .show()
    }

    fun buscarLista(id: String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = " SELECT * FROM LISTA WHERE ID_L = "+id
            var respuesta = transaccion.rawQuery(SQL, null)

            if (respuesta.moveToFirst()==true) {

                idL?.setText(respuesta.getString(0))
                descL?.setText(respuesta.getString(1))
                fechaL?.setText(respuesta.getString(2))
            }
            transaccion.close()

        } catch (err: SQLiteException) {
            mensaje("ERROR", "Error en busqueda")
        }

        mostrarTareas(id)
    }
    fun mostrarTareas(idL:String){

    }
    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("OK") { dialog, which -> }.show()
    }//Funcion mensaje


}//Class