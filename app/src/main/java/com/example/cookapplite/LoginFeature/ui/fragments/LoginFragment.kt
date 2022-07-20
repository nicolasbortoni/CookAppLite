package com.example.cookapplite.LoginFeature.ui.fragments


import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cookapplite.LoginFeature.ui.navigatorStates.LoginNavigatorStates
import com.example.cookapplite.R
import com.example.cookapplite.databinding.LoginFragmentBinding
import com.example.cookapplite.LoginFeature.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        setObservers()

        binding.loginBtn.setOnClickListener {
            viewModel.loginUsers(binding.emailEditText.text.toString(), binding.passEditText.text.toString())
        }
        binding.userMessage.setOnClickListener {
            viewModel.goToAddUser()
        }
    }

    private fun setObservers(){
        with(viewModel){
            navigation.observe(viewLifecycleOwner, Observer { handleNavigation(it) })
        }
    }

    private fun handleNavigation(loginNavigatorStates: LoginNavigatorStates){
        when(loginNavigatorStates){
            is LoginNavigatorStates.ToMainActivity -> {
                val action = LoginFragmentDirections.actionLoginFragmentToMainActivity()
                findNavController().navigate(action)
            }
            is LoginNavigatorStates.ToAddUserFragment -> {
                val action = LoginFragmentDirections.actionLoginFragmentToAddUserFragment()
                findNavController().navigate(action)
            }
        }
    }


    private fun showDialog(){
        val alertDialog : AlertDialog.Builder? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
            }
        }
        alertDialog?.setTitle("Recuperar contraseña")
        alertDialog?.setMessage("Se enviará un mail de recuperación al mail "
                + binding.emailEditText.text.toString())
        alertDialog?.create()
        alertDialog?.show()
    }
}