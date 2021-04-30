package KM.Alif.auth.adapter

import KM.Alif.auth.model.Barang
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.auth.databinding.ListItemBinding

class MainAdapter(private var stuff:List<Barang>):RecyclerView.Adapter<MainAdapter.StuffViewholder>() {

    inner class StuffViewholder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StuffViewholder {
        return StuffViewholder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return stuff.size
    }

    override fun onBindViewHolder(holder: StuffViewholder, position: Int) {
        holder.binding.apply {
            TvNamaBrg.text = stuff[position].nama
        }
    }
}