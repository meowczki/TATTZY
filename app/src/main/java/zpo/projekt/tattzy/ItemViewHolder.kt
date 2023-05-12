package zpo.projekt.tattzy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.image_view)
    val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
}