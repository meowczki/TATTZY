package zpo.projekt.tattzy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import zpo.projekt.tattzy.databinding.FragmentExploreBinding
import zpo.projekt.tattzy.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var auth: FirebaseAuth;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_LoginFragment)
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_RegisterFragment)
        }
        binding.skip.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_ExploreFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}