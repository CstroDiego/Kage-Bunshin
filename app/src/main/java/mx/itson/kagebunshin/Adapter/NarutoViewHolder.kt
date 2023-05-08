package mx.itson.kagebunshin.Adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.itson.kagebunshin.R
import mx.itson.kagebunshin.entity.Shinobi
import org.w3c.dom.Text

class NarutoViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val nombre = view.findViewById<TextView>(R.id.tvNombre)
    val clan = view.findViewById<TextView>(R.id.tvClan)
    val aldea = view.findViewById<TextView>(R.id.tvAldea)
    val rango = view.findViewById<TextView>(R.id.tvRango)
    val descripcion = view.findViewById<TextView>(R.id.tvDescripcion)
    val imagen = view.findViewById<ImageView>(R.id.ivImagen)

    fun render(shinobi: Shinobi) {
        nombre.text = shinobi.nombre
        clan.text = shinobi.clan
        aldea.text = shinobi.aldea
        rango.text = shinobi.rango
        descripcion.text = shinobi.descripcion
        Picasso.get().load(shinobi.imagen).into(imagen)
    }
}