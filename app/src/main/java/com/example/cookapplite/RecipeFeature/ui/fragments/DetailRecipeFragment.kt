package com.example.cookapplite.RecipeFeature.ui.fragments

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
import com.bumptech.glide.Glide
import com.example.cookapplite.R
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.DetailRecipeNavigatorStates
import com.example.cookapplite.RecipeFeature.ui.viewmodel.DetailRecipeViewModel
import com.example.cookapplite.databinding.FragmentDetailRecipeBinding
import com.mana.template.core.ui.viewstates.BaseViewState
import com.mana.template.core.ui.viewstates.EditViewState
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

        setObservers()

        val recipe = DetailRecipeFragmentArgs.fromBundle(requireArguments()).recipeToDetail
        configureView(recipe)

        binding.deleteBtn.setOnClickListener {
            viewModel.deleteRecipe(recipe)
        }

    }

    private fun configureView(recipe : Recipe){
        binding.recipeTitleTextView.text = recipe.title
        binding.recipeAuthorTextView.text = recipe.author
        binding.recipeTextView.text = recipe.recipe
        binding.recipeDescriptionTextView.text = recipe.description

        Glide
            .with(requireContext())
            .load(recipe.image)
            .centerCrop()
            .into(binding.recipeImage)

        val user = viewModel.getUserData()

        if(recipe.ownerUid == user.uuid){
            binding.deleteBtn.visibility = View.VISIBLE
        }
        else{
            binding.deleteBtn.visibility =  View.GONE
        }



    }

    private fun setObservers(){
        with(viewModel){
            navigation.observe(viewLifecycleOwner, Observer { handleNavigation(it) })
            viewState.observe(viewLifecycleOwner, Observer { handleViewState(it) })
        }
    }

    private fun handleNavigation(detailRecipeNavigatorStates: DetailRecipeNavigatorStates){
        when(detailRecipeNavigatorStates){
            is DetailRecipeNavigatorStates.popBack -> findNavController().navigateUp()
        }
    }

    private fun handleViewState(viewState : BaseViewState){
        when(viewState){
            is BaseViewState.Idle -> {

            }
            is BaseViewState.Alert -> {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(viewState.title)
                builder.setMessage(viewState.message)
                    .setPositiveButton("Accept") { dialog, id -> viewState.callback?.invoke() }
                    .setNegativeButton("Cancel") { dialog, id ->  }
                builder.create().show()
            }
        }
    }
}