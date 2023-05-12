package mx.itson.kagebunshin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.itson.kagebunshin.R
import mx.itson.kagebunshin.model.Shinobi

class NarutoAdapter(private var shinobiList: List<Shinobi>) : RecyclerView.Adapter<NarutoViewHolder>() {


    fun updateList(list: List<Shinobi>) {
        shinobiList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NarutoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NarutoViewHolder(layoutInflater.inflate(R.layout.item_shinobi, parent, false))
    }

    override fun getItemCount(): Int = shinobiList.size


    override fun onBindViewHolder(holder: NarutoViewHolder, position: Int) {
        val item = shinobiList[position]
        holder.render(item)
    }


}