package zpo.projekt.tattzy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import zpo.projekt.tattzy.databinding.FragmentLoginBinding
import zpo.projekt.tattzy.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private lateinit var auth: FirebaseAuth;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            register()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun register() {
         val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()

        if ( email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this.requireContext(), "Fill in all the details", Toast.LENGTH_SHORT).show()
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_RegisterFragment_to_ExploreFragment)

                    } else {
                        //println(task.exception?.message)
                        Toast.makeText(this.requireContext(), "Unsuccessful", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}