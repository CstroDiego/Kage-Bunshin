package mx.itson.kagebunshin.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mx.itson.kagebunshin.R
import mx.itson.kagebunshin.`interface`.NarutoAPI
import mx.itson.kagebunshin.model.Shinobi
import mx.itson.kagebunshin.util.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShinobiFormActivity : AppCompatActivity(), View.OnClickListener {

    private var shinobi: Shinobi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shinobi_form)

        val btnGuardar = findViewById<View>(R.id.btnGuardar)
        btnGuardar.setOnClickListener(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            shinobi = intent.getSerializableExtra("shinobi", Shinobi::class.java)
        } else {
            shinobi = intent.getSerializableExtra("shinobi") as Shinobi?
        }


        if (shinobi != null) {
            val txtNombre = findViewById<EditText>(R.id.txtNombre)
            val txtRango = findViewById<EditText>(R.id.txtRango)
            val txtAldea = findViewById<EditText>(R.id.txtAldea)
            val txtClan = findViewById<EditText>(R.id.txtClan)
            val txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
            val txtImagen = findViewById<EditText>(R.id.txtImagen)

            txtNombre.setText(shinobi?.nombre)
            txtRango.setText(shinobi?.rango)
            txtAldea.setText(shinobi?.aldea)
            txtClan.setText(shinobi?.clan)
            txtDescripcion.setText(shinobi?.descripcion)
            txtImagen.setText(shinobi?.imagen)

        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnGuardar -> {
                if (shinobi != null) {
                    val txtNombre = findViewById<EditText>(R.id.txtNombre)
                    val txtRango = findViewById<EditText>(R.id.txtRango)
                    val txtAldea = findViewById<EditText>(R.id.txtAldea)
                    val txtClan = findViewById<EditText>(R.id.txtClan)
                    val txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)

                    // Crear objeto Shinobi con los datos del formulario
                    val nuevoShinobi = Shinobi(
                        shinobi!!.id,
                        txtNombre.text.toString(),
                        txtClan.text.toString(),
                        txtAldea.text.toString(),
                        txtRango.text.toString(),
                        txtDescripcion.text.toString(),
                        shinobi!!.imagen.toString()
                    )

                    // Llamar al método updateShinobi() del servicio de Retrofit
                    val retrofit = RetrofitUtil.getApi()
                    val call = retrofit.updateShinobi(shinobi!!.id, nuevoShinobi)

                    call.enqueue(object : Callback<Shinobi> {
                        override fun onResponse(call: Call<Shinobi>, response: Response<Shinobi>) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    this@ShinobiFormActivity,
                                    "Shinobi actualizado exitosamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            } else {
                                val errorResponse = response.errorBody()?.string()
                                Toast.makeText(
                                    this@ShinobiFormActivity,
                                    "Error al actualizar Shinobi: $errorResponse",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.i("ShinobiFormActivity", nuevoShinobi.id.toString())
                            }
                        }

                        override fun onFailure(call: Call<Shinobi>, t: Throwable) {
                            Log.e("ShinobiFormActivity", "Error al actualizar Shinobi: ${t.message}")
                            Toast.makeText(
                                this@ShinobiFormActivity,
                                "Error al actualizar Shinobi: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

                } else {
                    Toast.makeText(this, "No se encontró shinobi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}