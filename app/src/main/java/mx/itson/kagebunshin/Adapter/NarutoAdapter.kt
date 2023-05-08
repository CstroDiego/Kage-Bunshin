package mx.itson.kagebunshin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.itson.kagebunshin.R
import mx.itson.kagebunshin.entity.Shinobi

class NarutoAdapter(private val ShinobiList: List<Shinobi>) :
    RecyclerView.Adapter<NarutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NarutoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NarutoViewHolder(layoutInflater.inflate(R.layout.item_shinobi, parent, false))
    }

    override fun getItemCount(): Int = ShinobiList.size


    override fun onBindViewHolder(holder: NarutoViewHolder, position: Int) {
        val item = ShinobiList[position]
        holder.render(item)
    }


}