package com.example.cookapplite.RecipeFeature.ui.fragments

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
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.AddRecipeNavigatorStates
import com.example.cookapplite.databinding.AddRecipeFragmentBinding
import com.example.cookapplite.RecipeFeature.ui.viewmodel.AddRecipeViewModel
import com.mana.template.core.ui.viewstates.BaseViewState
import dagger.hilt.android.AndroidEntryPoint
import java.security.KeyStore

@AndroidEntryPoint
class AddRecipeFragment : Fragment() {

    companion object {
        fun newInstance() = AddRecipeFragment()
    }

    private val viewModel : AddRecipeViewModel by viewModels()
    private lateinit var binding : AddRecipeFragmentBinding

    private var recipeImage : Uri? = null

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            recipeImage = result.data?.data
            binding.recipeImageView.setImageURI(recipeImage)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddRecipeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        setObservers()

        binding.recipeImageView.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher.launch(gallery)
        }

        binding.createRecipeBtn.setOnClickListener {
            val newRecipe = Recipe(
                null,
                null,
                binding.recipeTitleEditText.text.toString(),
                binding.recipeAuthorEditText.text.toString(),
                binding.recipeEditText.text.toString(),
                null,
                binding.recipeDescriptionEditText.text.toString()
            )
            viewModel.createNewRecipe(newRecipe, recipeImage)
        }

    }

    private fun setObservers(){
        with(viewModel){
            navigation.observe(viewLifecycleOwner, Observer { handleNavigation(it) })
            viewState.observe(viewLifecycleOwner, Observer { handleViewState(it) })
        }
    }

    private fun handleViewState(viewState : BaseViewState){
        when(viewState){
            is BaseViewState.Loading -> {
                binding.scrollView2.visibility = View.GONE
                binding.progressBar2.visibility = View.VISIBLE
            }
            is BaseViewState.Idle -> {
                binding.scrollView2.visibility = View.VISIBLE
                binding.progressBar2.visibility = View.GONE
            }
        }
    }

    private fun handleNavigation(addRecipeNavigatorStates: AddRecipeNavigatorStates){
        when(addRecipeNavigatorStates){
            is AddRecipeNavigatorStates.PopBack -> findNavController().navigateUp()
        }
    }

}