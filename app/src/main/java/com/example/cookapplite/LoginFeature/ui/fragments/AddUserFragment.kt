package com.example.cookapplite.LoginFeature.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.LoginFeature.ui.navigatorStates.AddUserNavigatorStates
import com.example.cookapplite.LoginFeature.ui.navigatorStates.LoginNavigatorStates
import com.example.cookapplite.databinding.AddUserFragmentBinding
import com.example.cookapplite.LoginFeature.ui.viewmodel.AddUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment() {

    companion object {
        fun newInstance() = AddUserFragment()
    }

    private val viewModel: AddUserViewModel by viewModels()
    private lateinit var binding : AddUserFragmentBinding

    private var image : Uri? = null

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            image = result.data?.data
            binding.profileImage.setImageURI(image)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddUserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()

        binding.pickProfileImageBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher.launch(gallery)
        }

        binding.signUpBtn.setOnClickListener {
            val newUser =
                User(null,
                    null,
                    binding.newEmailEditText.text.toString(),
                    binding.newPhoneEditText.text.toString(),
                    binding.newBirthDayEditText.text.toString(),
                    null
                )
            viewModel.createNewUser(newUser, binding.newPassEditText.text.toString(), image)
        }

    }

    private fun goToLogin(){
        val action = AddUserFragmentDirections.actionAddUserFragmentToMainActivity()
        findNavController().navigate(action)
    }

    private fun setObservers(){
        with(viewModel){
            navigation.observe(viewLifecycleOwner, Observer { handleNavigation(it) })
        }
    }

    private fun handleNavigation(addUserNavigatorStates: AddUserNavigatorStates){
        when(addUserNavigatorStates){
            is AddUserNavigatorStates.ToMainActivity -> {
                val action = AddUserFragmentDirections.actionAddUserFragmentToMainActivity()
                findNavController().navigate(action)
            }
        }
    }
}

