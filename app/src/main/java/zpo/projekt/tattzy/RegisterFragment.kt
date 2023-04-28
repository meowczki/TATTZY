package zpo.projekt.tattzy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import zpo.projekt.tattzy.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val args: RegisterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        database = Firebase.database.reference

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val clientType = args.clientType
        val ref=database.ref.toString()
        Snackbar.make(view, ref, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        binding.button.setOnClickListener {
            //sprawdzam czy przekazało typ klienta

            register(clientType)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun register(clientType: String) {
         val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        val username="ola"
        val name="olla"
        val phone="12312312"
        val birthday = "24-03-2000"
        if ( email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this.requireContext(), "Fill in all the details", Toast.LENGTH_SHORT).show()
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->


                    if (task.isSuccessful) {

                        writeNewUser(auth.currentUser!!.uid,username,email,name,phone,birthday,ClientType.valueOf(clientType.uppercase()))
                        findNavController().navigate(R.id.action_RegisterFragment_to_ExploreFragment)

                    } else {
                        //println(task.exception?.message)
                        Toast.makeText(this.requireContext(), "Unsuccessful", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    fun writeNewUser(userId: String, username: String? = null,  email: String? = null, name: String? = null, phone: String? = null, birthday: String? = null, clientType: ClientType? = null) {
        val user = User(username,email,name,phone,birthday,clientType)

        database.child("users").child(userId).setValue(user)
    }
}