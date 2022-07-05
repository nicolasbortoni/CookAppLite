package com.example.cookapplite.RecipeFeature.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.databinding.AddRecipeFragmentBinding
import com.example.cookapplite.RecipeFeature.ui.viewmodel.AddRecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRecipeFragment : Fragment() {

    companion object {
        fun newInstance() = AddRecipeFragment()
    }

    private lateinit var viewModel: AddRecipeViewModel
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

        binding.recipeImageView.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher.launch(gallery)
        }

        binding.createRecipeBtn.setOnClickListener {
            val newRecipe = Recipe(
                null,
                binding.recipeTitleEditText.text.toString(),
                binding.recipeAuthorEditText.text.toString(),
                binding.recipeEditText.text.toString(),
                null,
                null
            )
            val private = binding.privateCheckBox.isChecked

            viewModel.createNewRecipe(newRecipe, recipeImage)

        }

    }

}