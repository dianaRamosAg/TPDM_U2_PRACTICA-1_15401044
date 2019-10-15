package mx.edu.ittepic.tpdm_u2_pratica1_15401044

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class BaseDatos(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version : Int
)   :SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(p0: SQLiteDatabase?) {
        //Tabla para listas
        p0?.execSQL("CREATE TABLE LISTA (ID_L INTEGER PRIMARY KEY AUTOINCREMENT, DESCRIPCION VARCHAR(400), FECHACREACION DATE)")

        //Tabla para tareas
        p0?.execSQL("CREATE TABLE TAREA (ID_T INTEGER PRIMARY KEY AUTOINCREMENT, DESCRIPCION VARCHAR(400), REALIZADO DATE, ID_LISTA INTEGER, FOREIGN KEY (ID_LISTA) REFERENCES LISTA (ID_L))")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //SE EJECUTA SI Y SOLO SI,LA VERSION DE LA BD DE SQLITE
        // Y LA VERSION DE LA BD DE LA APP(TRAS UNA ACTUALIZACION) SON DIFERENTES
    }
}