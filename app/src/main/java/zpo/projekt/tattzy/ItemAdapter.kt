package zpo.projekt.tattzy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

 class ItemAdapter(val items: List<Item>) : RecyclerView.Adapter<ItemViewHolder>() {
     private var listener: ((position: Int) -> Unit)? = null

     fun setOnItemClickListener(listener: (position: Int) -> Unit) {
         this.listener = listener
     }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_explore, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.titleTextView.text = item.title
        holder.itemView.setOnClickListener {
            listener?.invoke(position)
        }
    }

    override fun getItemCount() = items.size

 }