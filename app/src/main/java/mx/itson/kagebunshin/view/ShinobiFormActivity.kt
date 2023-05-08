package mx.itson.kagebunshin.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.itson.kagebunshin.R
import mx.itson.kagebunshin.model.Shinobi

class ShinobiFormActivity : AppCompatActivity() {

    private var shinobi: Shinobi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shinobi_form)

    }

}