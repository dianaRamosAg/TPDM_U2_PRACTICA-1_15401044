package mx.edu.ittepic.tpdm_u2_pratica1_15401044


import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main4.*

class Main4Activity : AppCompatActivity() {
    var btnReg: Button? = null
    var btnBuscar: Button? = null
    var matareas: Button? = null
    var basedatos = BaseDatos(this, "PRACTICA1", null, 1)   //Conexion para SQLite
    var idL: EditText? = null
    var descL: EditText? = null
    var fechaL: EditText? = null
    var ListasM : ListView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        btnReg = findViewById(R.id.btnRegresar)
        btnBuscar = findViewById(R.id.btnBuscarLista)
        idL = findViewById(R.id.txtIDLista)
        descL = findViewById(R.id.txtDescripcionLista)
        fechaL = findViewById(R.id.txtFechaCreacionLista)
        ListasM=findViewById(R.id.ListarTareas)

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
       // mensaje("este es el id",""+id)
        mostrarTareas(id)
    }
    fun mostrarTareas(idL:String){

        var ListasGen: ArrayList<String> = ArrayList()

            try {
                var transaccion = basedatos.readableDatabase
                var SQL="SELECT * FROM TAREA"
                var  cursor = transaccion.rawQuery(SQL,null)

                if (cursor.moveToFirst()==true){
                    mensaje("ERROR", ""+cursor.getString(0)+cursor.getString(1))
                  /*  do{
                        ListasGen?.add(cursor.getString(1)+" | "+cursor.getString(2)+" | "+cursor.getString(3))
                    }while(cursor.moveToNext()) */
                }
                cursor.close()
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ListasGen)
                ListarTareas?.setAdapter(adapter);

            }catch (err: SQLiteException){
                mensaje("ERROR", "error")
            }


    }
    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("OK") { dialog, which -> }.show()
    }//Funcion mensaje


}//Class