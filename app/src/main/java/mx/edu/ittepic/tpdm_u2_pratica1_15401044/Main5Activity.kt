package mx.edu.ittepic.tpdm_u2_pratica1_15401044


import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class Main5Activity : AppCompatActivity() {
    var btnReg:Button?=null
    var basedatos = BaseDatos(this, "PRACTICA1", null, 1)   //Conexion para SQLite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        btnReg=findViewById(R.id.btnRegresar)

        btnReg?.setOnClickListener {
            var principal = Intent(this,MainActivity::class.java)
            startActivity(principal)
        }





    }

    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("OK") { dialog, which -> }.show()
    }//Funcion mensaje




}//CLASS
