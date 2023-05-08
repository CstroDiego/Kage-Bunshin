package mx.itson.kagebunshin.Adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import mx.itson.kagebunshin.model.Shinobi


class ShinobiAdapter(context: Context?, shinobis: List<Shinobi>) : BaseAdapter() {
    var context: Context? = context
    var shinobis: List<Shinobi> = shinobis
    override fun getCount(): Int {
        return shinobis.size
    }

    override fun getItem(p0: Int): Any {
        return shinobis[p0]
    }

    override fun getItemId(p0: Int): Long {
        return shinobis[p0].id!!.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val renglon = View.inflate(context, mx.itson.kagebunshin.R.layout.item_shinobi, null)
        try {
            val shinobi = getItem(p0) as Shinobi
            val txtNombre = renglon.findViewById<TextView>(mx.itson.kagebunshin.R.id.txtNombre)
            val txtClan = renglon.findViewById<TextView>(mx.itson.kagebunshin.R.id.txtClan)
            val txtAldea = renglon.findViewById<TextView>(mx.itson.kagebunshin.R.id.txtAldea)
            val txtRango = renglon.findViewById<TextView>(mx.itson.kagebunshin.R.id.txtRango)
            val txtDescripcion = renglon.findViewById<TextView>(mx.itson.kagebunshin.R.id.txtDescripcion)
            val txtImagen = renglon.findViewById<TextView>(mx.itson.kagebunshin.R.id.txtImagen)

            txtNombre.text = shinobi.nombre
            txtClan.text = shinobi.clan
            txtAldea.text = shinobi.aldea
            txtRango.text = shinobi.rango
            txtDescripcion.text = shinobi.descripcion
            txtImagen.text = shinobi.imagen

        }catch (ex: Exception) {
            Log.e("ShinobiAdapter", ex.toString())
        }
        return renglon
    }
}