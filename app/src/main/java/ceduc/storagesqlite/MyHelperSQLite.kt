package ceduc.storagesqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelperSQLite(context:Context): SQLiteOpenHelper(context, "usuarios.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var makeQuery = "CREATE TABLE usuario(rut INTEGER PRIMARY KEY, nombre TEXT, email TEXT)"
        db!!.execSQL(makeQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val delQuery = "DROP TABLE IF EXISTS usuario"
        db!!.execSQL(delQuery)
        onCreate(db)
    }

    fun guardar(rut:Int, nombre:String, email:String)
    {
        val datos = ContentValues();//Paquete de datos

        datos.put("rut", rut)
        datos.put("nombre", nombre)
        datos.put("email", email)

        val db = this.writableDatabase //Modo Edición  de la BD
        db.insert("usuario",null,datos)
        db.close()
    }
}