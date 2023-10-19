package ceduc.storagesqlite

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class
MainActivity : AppCompatActivity() {

    lateinit var oh:MyHelperSQLite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        oh = MyHelperSQLite(this)
        var rut = findViewById<EditText>(R.id.txtCode)
        var name = findViewById<EditText>(R.id.txtNombre)
        var mail = findViewById<EditText>(R.id.txtMail)

        var save = findViewById<Button>(R.id.btnSave)
        var find = findViewById<Button>(R.id.btnSearch)

        save.setOnClickListener {
            if (rut.text.isNotBlank() && name.text.isNotBlank())
            {
                oh.guardar(rut.text.toString().toInt(), name.text.toString(), mail.text.toString())
                rut.text.clear()
                name.text.clear()
                mail.text.clear()
                Toast.makeText(this, "Dato Guardado", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_LONG).show()
            }
        }

        find.setOnClickListener {
            val db : SQLiteDatabase = oh.readableDatabase
            val code = rut.text.toString().toInt()
            val cursor = db.rawQuery("SELECT * FROM usuario", null)

            if (cursor.moveToFirst())
            {
                do{
                   /* rut.setText(cursor.getInt(0))
                    name.setText(cursor.getString(1))
                    mail.setText(cursor.getString(2))*/
                }while (cursor.moveToNext())
            }
            else
            {
                Toast.makeText(this,"No se pudo", Toast.LENGTH_LONG).show()
            }
        }
    }
}