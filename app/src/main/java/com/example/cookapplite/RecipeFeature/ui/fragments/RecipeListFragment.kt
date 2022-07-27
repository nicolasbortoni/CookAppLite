package com.example.cookapplite.RecipeFeature.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.RecipeListNavigatorStates
import com.example.cookapplite.RecipeFeature.ui.adapters.RecipeAdapter
import com.example.cookapplite.RecipeFeature.ui.viewmodel.RecipeListViewModel
import com.example.cookapplite.databinding.RecipeListFragmentBinding
import com.mana.template.core.ui.viewstates.BaseViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel : RecipeListViewModel by viewModels()
    private lateinit var binding : RecipeListFragmentBinding
    private var recipeAdapter = RecipeAdapter()

    companion object {
        fun newInstance() = RecipeListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        setObservers()
        setupRecyclerView()
        viewModel.getRecipes()

        binding.addRecipeBtn.setOnClickListener {
            viewModel.goToAddRecipe()
        }
    }

    private fun setupRecyclerView(){
        recipeAdapter.clickLister = {recipe -> itemClickListener(recipe)}
        recipeAdapter.likeListener = {recipe -> likeClickListener(recipe)}
        recipeAdapter.context = requireContext()
        with(binding){
            recipesRecycler.setHasFixedSize(true)
            recipesRecycler.layoutManager  = LinearLayoutManager(context)
            recipesRecycler.adapter = recipeAdapter
        }
    }

    private fun setObservers(){
        with(viewModel){
            navigation.observe(viewLifecycleOwner, Observer { handleNavigation(it) })
            viewState.observe(viewLifecycleOwner, Observer { handleViewState(it) })
            recipeList.observe(viewLifecycleOwner, Observer{ list ->
                recipeAdapter.setData(list)
            })
        }
    }

    private fun handleNavigation(navigation: RecipeListNavigatorStates) {
        when (navigation) {
            is RecipeListNavigatorStates.toAddRecipeFragment -> {
                val action = RecipeListFragmentDirections.actionRecipeListFragmentToAddRecipeFragment(
                    Recipe()
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun handleViewState(viewState : BaseViewState){
        when(viewState){
            is BaseViewState.Loading -> {
                binding.addRecipeBtn.visibility = View.GONE
                binding.recipesRecycler.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            is BaseViewState.Idle -> {
                binding.addRecipeBtn.visibility = View.VISIBLE
                binding.recipesRecycler.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun itemClickListener(recipe : Recipe){
        val action = RecipeListFragmentDirections.actionRecipeListFragmentToDetailRecipeFragment(recipe)
        findNavController().navigate(action)
    }

    private fun likeClickListener(recipe: Recipe){
        viewModel.likeRecipe(recipe)
    }

}