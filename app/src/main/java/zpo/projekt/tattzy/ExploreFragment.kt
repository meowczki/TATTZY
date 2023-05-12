package zpo.projekt.tattzy

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import zpo.projekt.tattzy.databinding.FragmentExploreBinding
import zpo.projekt.tattzy.databinding.FragmentHomeBinding
import zpo.projekt.tattzy.databinding.FragmentLoginBinding
import java.io.File


class ExploreFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ItemAdapter(
            listOf(
                Item("Artysta 1", R.drawable.item1),
                Item("Artysta 2", R.drawable.item1),
                Item("Artysta 3", R.drawable.item1),
                Item("Artysta 4", R.drawable.item1),
                Item("Artysta 5", R.drawable.item1),
                Item("Artysta 6", R.drawable.item1)

            )
        )
        binding.fabProfile.setOnClickListener {
            val fragment = ProfileFragment()
            findNavController().navigate(R.id.action_ExploreFragment_to_ProfileFragment)
        }
        recyclerView.adapter = adapter

    }

    private class Item(val title: String, val imageResId: Int)

    private class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
    }

    private class ItemAdapter(val items: List<Item>) : RecyclerView.Adapter<ItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_explore, parent, false)
            return ItemViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = items[position]
            holder.imageView.setImageResource(item.imageResId)
            holder.titleTextView.text = item.title
        }

        override fun getItemCount() = items.size
    }

}