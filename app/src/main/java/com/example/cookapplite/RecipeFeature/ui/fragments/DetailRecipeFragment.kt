package com.example.cookapplite.RecipeFeature.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.cookapplite.R
import com.example.cookapplite.RecipeFeature.ui.viewmodel.DetailRecipeViewModel
import com.example.cookapplite.databinding.FragmentDetailRecipeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRecipeFragment : Fragment() {

    companion object {
        fun newInstance() = DetailRecipeFragment()
    }

    private val viewModel : DetailRecipeViewModel by viewModels()
    private lateinit var binding : FragmentDetailRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val recipe = DetailRecipeFragmentArgs.fromBundle(requireArguments()).recipeToDetail

        binding.recipeTitleTextView.text = recipe.title
        binding.recipeAuthorTextView.text = recipe.author
        binding.recipeTextView.text = recipe.recipe
        binding.recipeDescriptionTextView.text = recipe.description

        Glide
            .with(requireContext())
            .load(recipe.image)
            .centerCrop()
            .into(binding.recipeImage)

    }

}