package zpo.projekt.tattzy

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import zpo.projekt.tattzy.databinding.FragmentExploreBinding
import java.io.File


class ExploreFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        // odwołanie do RecyclerView z pliku layoutu fragment_explore.xml
        val recyclerView = binding.recyclerView

        // właściwości dla RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val folderPath = "/sampledata"
        val files = getImagesFromFolder(folderPath)

        val adapter = MyRecyclerViewAdapter(requireContext(), files)
        recyclerView.adapter = adapter

        binding.logoutButton.setOnClickListener {
            if(auth.currentUser!=null){
                auth.signOut()

            }
            findNavController().navigate(R.id.action_ExploreFragment_to_HomeFragment)
        }

        binding.toProfile.setOnClickListener {
            findNavController().navigate(R.id.action_ExploreFragment_to_ProfileFragment)
        }

        binding.fabChat.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getImagesFromFolder(folderPath: String): List<File> {
        val folder = File(folderPath)
        return folder.listFiles()?.filter { it.isFile && it.extension in listOf("jpg", "jpeg", "png") }?.toList() ?: emptyList()
    }
    inner class MyRecyclerViewAdapter(val context: Context, val files: List<File>) :
        RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val file = files[position]
            val uri = Uri.fromFile(file)
            Glide.with(context)
                .load(uri)
                .placeholder(R.drawable.ic_image_placeholder)
                .centerCrop()
                .into(holder.imageView)
        }

        override fun getItemCount() = files.size

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
        }
    }
}
