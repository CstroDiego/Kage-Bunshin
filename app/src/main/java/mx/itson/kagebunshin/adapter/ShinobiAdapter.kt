package mx.itson.kagebunshin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import mx.itson.kagebunshin.R
import mx.itson.kagebunshin.model.Shinobi

class ShinobiAdapter(private var shinobis: List<Shinobi>) : BaseAdapter() {

    override fun getCount(): Int {
        return shinobis.size
    }

    override fun getItem(position: Int): Any {
        return shinobis[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_shinobi, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val shinobi = shinobis[position]

        holder.nombre.text = shinobi.nombre
        holder.clan.text = shinobi.clan
        holder.aldea.text = shinobi.aldea
        holder.rango.text = shinobi.rango
        holder.descripcion.text = shinobi.descripcion
        Picasso.get().load(shinobi.imagen).into(holder.imagen)

        return view
    }

    fun updateList(newShinobis: List<Shinobi>) {
        shinobis = newShinobis
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) {
        val nombre = view.findViewById<TextView>(R.id.tvNombre)
        val clan = view.findViewById<TextView>(R.id.tvClan)
        val aldea = view.findViewById<TextView>(R.id.tvAldea)
        val rango = view.findViewById<TextView>(R.id.tvRango)
        val descripcion = view.findViewById<TextView>(R.id.tvDescripcion)
        val imagen = view.findViewById<ImageView>(R.id.ivImagen)
    }
}
