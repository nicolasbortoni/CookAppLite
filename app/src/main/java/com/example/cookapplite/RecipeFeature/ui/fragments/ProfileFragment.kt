package com.example.cookapplite.RecipeFeature.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.R
import com.example.cookapplite.RecipeFeature.ui.viewmodel.ProfileViewModel
import com.example.cookapplite.databinding.AddRecipeFragmentBinding
import com.example.cookapplite.databinding.ProfileFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding : ProfileFragmentBinding

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val user = viewModel.getProfileData()
        setProfileView(user)
    }

    private fun setProfileView(user : User){
        binding.userEmailTextView.text = user.email
        binding.userBirthdayTextView.text = user.birthday
        binding.userPhoneTextView.text = user.phone
        binding.usernameTextView.text = user.username

        Glide
            .with(requireContext())
            .load(user.profileImage)
            .centerCrop()
            .into(binding.profileImage)

    }

}